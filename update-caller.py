import os
import ConfigParser
import subprocess

configurationFile='/tmp/Dashboard/install.ini'

config = ConfigParser.ConfigParser()
print('Reading configuration from ' + configurationFile)
config.read(configurationFile)

profileName = config.get('WebSphere', 'profileName')

subprocess.call(["/data/IBM/WebSphere/AppServer/profiles/" + profileName + "/bin/wsadmin.sh", "-user", "wasadmin", "-password", "adminwas", "-lang", "jython", "-f", "/tmp/Dashboard/update.py"] )