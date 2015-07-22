"""
This script is used to deploy application to web server, IBM WebSphere, by using wsadmin client with Jython language.

Author : Saroj Sangphongamphai (saroj_san@truecorp.co.th)
Modify : Chayakorn P (chayakorn_pon@truecorp.co.th)
Date created : Saturday 4th October 2014
Last update : Wednesday 04th February 2015
"""

import os
import sys
import shutil
import time
import ConfigParser

configurationFile='/tmp/Dashboard/install.ini'

config = ConfigParser.ConfigParser()
print('Reading configuration from ' + configurationFile)
config.read(configurationFile)


appName = config.get('WebSphere', 'appName')
moduleName = config.get('WebSphere', 'moduleName')
node = config.get('WebSphere', 'node')
cell = config.get('WebSphere', 'cell')
cluster = config.get('WebSphere', 'cluster')
server = config.get('WebSphere', 'server')
warFile = config.get('WebSphere', 'warFile')
artifact = config.get('WebSphere', 'artifact')
contextroot = config.get('WebSphere', 'contextroot')

def installApp(artifact, node, cell, server, cluster):
	print '>>> Installing application'
	option = [
        '-node', node,
        '-cell', cell,
        '-server', server,
        '-cluster', cluster,
        '-appname', appName,
        '-MapModulesToServers', [['.*', '.*.war,.*', 'WebSphere:cell=%s,cluster=%s+WebSphere:cell=%s,node=ihsnode,server=webserver1' % (cell, cluster, cell)]],
        '-MapWebModToVH', [[moduleName, warFile + ',WEB-INF/web.xml', 'default_host' ]],
        '-CtxRootForWebMod', [[ moduleName, warFile + ',WEB-INF/web.xml', '/Dashboard' ]],
        '-MapResRefToEJB', [[moduleName,'', warFile + ',WEB-INF/web.xml', 'jdbc/silog', 'javax.sql.DataSource', 'jdbc/silog', '', '', '' ]] ,
        '-createMBeansForResources',
        '-preCompileJSPs',
        '-distributeApp',
        '-nouseMetaDataFromBinary',
        '-nodeployejb',
        '-createMBeansForResources',
        '-noreloadEnabled',
        '-nodeployws',
        '-validateinstall', 'warn',
        '-noprocessEmbeddedConfig',
        '-filepermission', '.*\.dll=755#.*\.so=755#.*\.a=755#.*\.sl=755',
        '-noallowDispatchRemoteInclude',
        '-noallowServiceRemoteInclude',
        '-asyncRequestDispatchType', 'DISABLED',
        '-nouseAutoLink',
        '-noenableClientModule',
        '-clientMode', 'isolated',
        '-novalidateSchema',
        '-contextroot', contextroot,
        ]
	AdminApp.install(artifact, option)
	print '>>> Installed application'

def isAppExists(appName):
	return len(AdminConfig.getid('/Deployment:%s/' % appName)) > 0

def isAppStarted(appName):
	return len(AdminControl.completeObjectName('type=Application,name=%s,*' % appName)) > 0

def mapSharedLib(appName, sharedLibName):
	appId = AdminConfig.getid('/Deployment:%s/' % appName)
	deployedObj = AdminConfig.showAttribute(appId, 'deployedObject')
	classLoader = AdminConfig.showAttribute(deployedObj, 'classloader')
	AdminConfig.create('LibraryRef', classLoader, [['libraryName', sharedLibName]])
	print '>>> Mapped shared library'

def saveConfig():
	AdminConfig.save()
	print '>>> The configuration was saved'

def stopApp(appName, node, server):
	if isAppStarted(appName):
		appManager = AdminControl.queryNames('type=ApplicationManager,node=%s,process=%s,*' % (node, server))
		AdminControl.invoke(appManager, 'stopApplication', appName)
	print '>>> Stopped application'

def startApp(appName, node, server):
	print '>>> Starting application'
	appManager = AdminControl.queryNames('type=ApplicationManager,node=%s,process=%s,*' % (node, server))
	AdminControl.invoke(appManager, 'startApplication', appName)
	print '>>> Started application'

def syncNode(node):
	nodeId = AdminControl.completeObjectName('type=NodeSync,node=%s,*' % node)
	AdminControl.invoke(nodeId, 'sync')
	print '>>> Node was synchronized'

def updateApp(appName, artifact):
	AdminApp.update(appName, 'app', '[-operation update -contents %s]' % artifact)
	isAppReady = AdminApp.isAppReady(appName)
	while (isAppReady == 'false'):
		time.sleep(3)
		isAppReady = AdminApp.isAppReady(appName)
	print '>>> Updated application'

def restartApp(appServer):
    print '>>> Restarting AppServer: ' + appServer
    AdminControl.invoke('WebSphere:name=ApplicationManager,process=%s,platform=proxy,node=%s,version=8.0.0.7,type=ApplicationManager,mbeanIdentifier=ApplicationManager,cell=%s,spec=1.0' % (appServer, node, cell), 'stopApplication', '[%s]' % appName, '[java.lang.String]')
    AdminControl.invoke('WebSphere:name=ApplicationManager,process=%s,platform=proxy,node=%s,version=8.0.0.7,type=ApplicationManager,mbeanIdentifier=ApplicationManager,cell=%s,spec=1.0' % (appServer, node, cell), 'startApplication', '[%s]' % appName, '[java.lang.String]')

print '>>> Begin of deployment script'

if isAppExists(appName):
	print '>>> The application is exists, begin the update process'
	
	#stopApp(appName, node, server)
	updateApp(appName, artifact)
	saveConfig()
	syncNode(node)
	time.sleep(10)

	appServers = server.split(',')
	for appServer in appServers:
	    restartApp(appServer)

	#startApp(appName, node, server)
else:
	print '>>> The application is not exists, begin the installation process'

	installApp(artifact, node, cell, server, cluster)
	saveConfig()
	syncNode(node)
	time.sleep(10)
	startApp(appName, node, server)

print '>>> End of deployment script'
