Name            : %{?projectname}
Summary         : %{?projectname}
Version         : %{?version}
Release         : %{?release}%{!?release:0}

Group           : Applications/File
License         : True Money

BuildArch       : %{?buildarch}%{!?buildarch:x86_64}
BuildRoot       : %{_tmppath}/%{name}-%{version}-root


# Use "Requires" for any dependencies, for example:
# Requires        : tomcat6

# Description gives information about the rpm package. This can be expanded up to multiple lines.
%description
%{name}


# Prep is used to set up the environment for building the rpm package
# Expansion of source tar balls are done in this section
%prep

# Used to compile and to build the source
%build

# The installation.
# We actually just put all our install files into a directory structure that mimics a server directory structure here
%install
rm -rf $RPM_BUILD_ROOT
install -d -m 755 $RPM_BUILD_ROOT/tmp/%{name}
cp ../SOURCES/%{name}*.war $RPM_BUILD_ROOT/tmp/%{name}/%{name}.war
cp ../SOURCES/update* $RPM_BUILD_ROOT/tmp/%{name}/

# Contains a list of the files that are part of the package
# See useful directives such as attr here: http://www.rpm.org/max-rpm-snapshot/s1-rpm-specref-files-list-directives.html
%files
%attr(755, root, -) /tmp/%{name}/%{name}*.war
%attr(755, root, -) /tmp/%{name}/update*

%post
/usr/bin/python /tmp/%{name}/update-caller.py > /tmp/%{name}/update.log

# Used to store any changes between versions
%changelog