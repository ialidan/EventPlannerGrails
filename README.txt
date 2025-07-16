# 1. Install SDKMAN (if you don’t already have it)
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# 2. Install the required Grails version
sdk install grails 5.3.6

# 3. Unzip the file and open a new terminal inside EventPlanner, and type this command
grails run‑app



#### Troubleshooting ####

If you are using Windows and can't install sdkman it's likely because the zip utility is missing from your system.

# 1. Download zip from the official GnuWin32 site:
https://gnuwin32.sourceforge.net/packages/zip.htm

# 2. After installation, locate the installation folder.
It’s typically:

C:\Program Files (x86)\GnuWin32\bin

# 3. Add that directory to your system's PATH environment variable:

Open System Properties → Advanced → Environment Variables

Under System Variables, find and edit Path

Add the path:
C:\Program Files (x86)\GnuWin32\bin

#4. Restart your terminal and try installing SDKMAN again.