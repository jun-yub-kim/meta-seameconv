<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="image/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">SEA:ME Custom OS (Conv version)</h3>

  <p align="center">
    A custom embedded linux OS for SEA:ME
    <br />
    <a href="https://github.com/jun-yub-kim/SEA-ME"><strong>Explore the SEA:ME project »</strong></a>
    <br />
    
  </p>
</div>


[If you are new about yocto](#How-to-use-Yocto)

[You know about yocto](#How-to-add-seameconv)
<br>
<br>
<br>
    
    


# How to use Yocto

`Linux`

## 1. Install dependencies

```jsx
sudo apt install build-essential chrpath gawk git bmap-tools texinfo diffstat
```

## 2. Make a directory to run yocto.
In my case I made it in the home directory

```jsx
mkdir ~yocto
cd ~/yocto
mkdir kirkstone
cd kirkstone
mkdir builds
mkdir downloads
```

## 3. Clone poky

```jsx
git clone -b kirkstone git://git.yoctoproject.org/poky.git
```

## 4. Clone poky raspberry pi extension / dependencies of raspberry pi extension

```jsx
git clone -b kirkstone https://github.com/agherzan/meta-raspberrypi.git
git clone -b kirkstone git://git.openembedded.org/meta-openembedded
```

## 5. Enter build environment

```jsx
source oe-init-build-env projectname
```
(You can use this command to make build directory.
In my case, I used this↓ command to make directory)
<details>
  <summary>In my case</summary>
  <div markdown="1">

    
    source ~/yocto/kirkstone/poky/oe-init-build-env ~/yocto/kirkstone/builds/rpi
    
    # It means, activate oe-init-build-env, and make build file at builds/rpi.
</details>
<br>

## 6. Add layers

Go to the directory just before you made.

```jsx
cd ~/yocto/kirkstone/builds/rpi/conf
vi bblayers.conf
```

add below lines.

```jsx
  /home/**username**/yocto/kirkstone/poky/meta \
  /home/**username**/yocto/kirkstone/poky/meta-poky \
  /home/**username**/yocto/kirkstone/poky/meta-yocto-bsp \
  /home/**username**/yocto/kirkstone/meta-openembedded/meta-oe \
  /home/**username**//yocto/kirkstone/meta-openembedded/meta-multimedia \
  /home/**username**/yocto/kirkstone/meta-openembedded/meta-networking \
  /home/**username**/yocto/kirkstone/meta-openembedded/meta-python \
  /home/**username**/yocto/kirkstone/meta-raspberrypi \
```

<details>
  <summary>
- bblayers.conf (in my case)</summary>
<div markdown="1">
    
    # POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
    # changes incompatibly
    POKY_BBLAYERS_CONF_VERSION = "2"
    
    BBPATH = "${TOPDIR}"
    BBFILES ?= ""
    
    BBLAYERS ?= " \
      /home/seame-fablab/yocto/kirkstone/poky/meta \
      /home/seame-fablab/yocto/kirkstone/poky/meta-poky \
      /home/seame-fablab/yocto/kirkstone/poky/meta-yocto-bsp \
      /home/seame-fablab/yocto/kirkstone/meta-openembedded/meta-oe \
      /home/seame-fablab//yocto/kirkstone/meta-openembedded/meta-multimedia \
      /home/seame-fablab/yocto/kirkstone/meta-openembedded/meta-networking \
      /home/seame-fablab/yocto/kirkstone/meta-openembedded/meta-python \
      /home/seame-fablab/yocto/kirkstone/meta-raspberrypi \
      "
  </details>
<br>

## 7. Update MACHINE and change directory 

```
cd ~/yocto/kirkstone/builds/rpi/conf
vi local.conf
```

```
MACHINE = "raspberrypi4"
#DL_DIR ?= "~/yocto/kirkstone/downloads"
ENABLE_UART = "1"
RPI_USE_U_BOOT = "1"
```

<details>
  <summary>local.conf (in my case)</summary>
<div markdown="1">

    
    ```
    also attached this
    at the end of line
    
    ENABLE_UART = "1"
    RPI_USE_U_BOOT = "1"
    
    ```
    
    ```
    #
    # This file is your local configuration file and is where all local user settings
    # are placed. The comments in this file give some guide to the options a new user
    # to the system might want to change but pretty much any configuration option can
    # be set in this file. More adventurous users can look at
    # local.conf.sample.extended which contains other examples of configuration which
    # can be placed in this file but new users likely won't need any of them
    # initially.
    #
    # Lines starting with the '#' character are commented out and in some cases the
    # default values are provided as comments to show people example syntax. Enabling
    # the option is a question of removing the # character and making any change to the
    # variable as required.
    
    #
    # Machine Selection
    #
    # You need to select a specific machine to target the build with. There are a selection
    # of emulated machines available which can boot and run in the QEMU emulator:
    #
    #MACHINE ?= "qemuarm"
    #MACHINE ?= "qemuarm64"
    #MACHINE ?= "qemumips"
    #MACHINE ?= "qemumips64"
    #MACHINE ?= "qemuppc"
    #MACHINE ?= "qemux86"
    #MACHINE ?= "qemux86-64"
    MACHINE ?= "raspberrypi4"
    #
    # There are also the following hardware board target machines included for 
    # demonstration purposes:
    #
    #MACHINE ?= "beaglebone-yocto"
    #MACHINE ?= "genericx86"
    #MACHINE ?= "genericx86-64"
    #MACHINE ?= "edgerouter"
    #
    # This sets the default machine to be qemux86-64 if no other machine is selected:
    MACHINE = "raspberrypi4"
    
    #
    # Where to place downloads
    #
    # During a first build the system will download many different source code tarballs
    # from various upstream projects. This can take a while, particularly if your network
    # connection is slow. These are all stored in DL_DIR. When wiping and rebuilding you
    # can preserve this directory to speed up this part of subsequent builds. This directory
    # is safe to share between multiple builds on the same machine too.
    #
    # The default is a downloads directory under TOPDIR which is the build directory.
    #
    #DL_DIR = "~/yocto/kirkstone/downloads"
    
    #
    # Where to place shared-state files
    #
    # BitBake has the capability to accelerate builds based on previously built output.
    # This is done using "shared state" files which can be thought of as cache objects
    # and this option determines where those files are placed.
    #
    # You can wipe out TMPDIR leaving this directory intact and the build would regenerate
    # from these files if no changes were made to the configuration. If changes were made
    # to the configuration, only shared state files where the state was still valid would
    # be used (done using checksums).
    #
    # The default is a sstate-cache directory under TOPDIR.
    #
    #SSTATE_DIR ?= "${TOPDIR}/sstate-cache"
    
    #
    # Where to place the build output
    #
    # This option specifies where the bulk of the building work should be done and
    # where BitBake should place its temporary files and output. Keep in mind that
    # this includes the extraction and compilation of many applications and the toolchain
    # which can use Gigabytes of hard disk space.
    #
    # The default is a tmp directory under TOPDIR.
    #
    #TMPDIR = "${TOPDIR}/tmp"
    
    #
    # Default policy config
    #
    # The distribution setting controls which policy settings are used as defaults.
    # The default value is fine for general Yocto project use, at least initially.
    # Ultimately when creating custom policy, people will likely end up subclassing 
    # these defaults.
    #
    DISTRO ?= "poky"
    # As an example of a subclass there is a "bleeding" edge policy configuration
    # where many versions are set to the absolute latest code from the upstream 
    # source control systems. This is just mentioned here as an example, its not
    # useful to most new users.
    # DISTRO ?= "poky-bleeding"
    
    #
    # Package Management configuration
    #
    # This variable lists which packaging formats to enable. Multiple package backends
    # can be enabled at once and the first item listed in the variable will be used
    # to generate the root filesystems.
    # Options are:
    #  - 'package_deb' for debian style deb files
    #  - 'package_ipk' for ipk files are used by opkg (a debian style embedded package manager)
    #  - 'package_rpm' for rpm style packages
    # E.g.: PACKAGE_CLASSES ?= "package_rpm package_deb package_ipk"
    # We default to rpm:
    PACKAGE_CLASSES ?= "package_rpm"
    
    #
    # SDK target architecture
    #
    # This variable specifies the architecture to build SDK items for and means
    # you can build the SDK packages for architectures other than the machine you are
    # running the build on (i.e. building i686 packages on an x86_64 host).
    # Supported values are i686, x86_64, aarch64
    #SDKMACHINE ?= "i686"
    
    #
    # Extra image configuration defaults
    #
    # The EXTRA_IMAGE_FEATURES variable allows extra packages to be added to the generated
    # images. Some of these options are added to certain image types automatically. The
    # variable can contain the following options:
    #  "dbg-pkgs"       - add -dbg packages for all installed packages
    #                     (adds symbol information for debugging/profiling)
    #  "src-pkgs"       - add -src packages for all installed packages
    #                     (adds source code for debugging)
    #  "dev-pkgs"       - add -dev packages for all installed packages
    #                     (useful if you want to develop against libs in the image)
    #  "ptest-pkgs"     - add -ptest packages for all ptest-enabled packages
    #                     (useful if you want to run the package test suites)
    #  "tools-sdk"      - add development tools (gcc, make, pkgconfig etc.)
    #  "tools-debug"    - add debugging tools (gdb, strace)
    #  "eclipse-debug"  - add Eclipse remote debugging support
    #  "tools-profile"  - add profiling tools (oprofile, lttng, valgrind)
    #  "tools-testapps" - add useful testing tools (ts_print, aplay, arecord etc.)
    #  "debug-tweaks"   - make an image suitable for development
    #                     e.g. ssh root access has a blank password
    # There are other application targets that can be used here too, see
    # meta/classes/image.bbclass and meta/classes/core-image.bbclass for more details.
    # We default to enabling the debugging tweaks.
    EXTRA_IMAGE_FEATURES ?= "debug-tweaks"
    
    #
    # Additional image features
    #
    # The following is a list of additional classes to use when building images which
    # enable extra features. Some available options which can be included in this variable
    # are:
    #   - 'buildstats' collect build statistics
    USER_CLASSES ?= "buildstats"
    
    #
    # Runtime testing of images
    #
    # The build system can test booting virtual machine images under qemu (an emulator)
    # after any root filesystems are created and run tests against those images. It can also
    # run tests against any SDK that are built. To enable this uncomment these lines.
    # See classes/test{image,sdk}.bbclass for further details.
    #IMAGE_CLASSES += "testimage testsdk"
    #TESTIMAGE_AUTO:qemuall = "1"
    
    #
    # Interactive shell configuration
    #
    # Under certain circumstances the system may need input from you and to do this it
    # can launch an interactive shell. It needs to do this since the build is
    # multithreaded and needs to be able to handle the case where more than one parallel
    # process may require the user's attention. The default is iterate over the available
    # terminal types to find one that works.
    #
    # Examples of the occasions this may happen are when resolving patches which cannot
    # be applied, to use the devshell or the kernel menuconfig
    #
    # Supported values are auto, gnome, xfce, rxvt, screen, konsole (KDE 3.x only), none
    # Note: currently, Konsole support only works for KDE 3.x due to the way
    # newer Konsole versions behave
    #OE_TERMINAL = "auto"
    # By default disable interactive patch resolution (tasks will just fail instead):
    PATCHRESOLVE = "noop"
    
    #
    # Disk Space Monitoring during the build
    #
    # Monitor the disk space during the build. If there is less that 1GB of space or less
    # than 100K inodes in any key build location (TMPDIR, DL_DIR, SSTATE_DIR), gracefully
    # shutdown the build. If there is less than 100MB or 1K inodes, perform a hard halt
    # of the build. The reason for this is that running completely out of space can corrupt
    # files and damages the build in ways which may not be easily recoverable.
    # It's necessary to monitor /tmp, if there is no space left the build will fail
    # with very exotic errors.
    BB_DISKMON_DIRS ??= "\
        STOPTASKS,${TMPDIR},1G,100K \
        STOPTASKS,${DL_DIR},1G,100K \
        STOPTASKS,${SSTATE_DIR},1G,100K \
        STOPTASKS,/tmp,100M,100K \
        HALT,${TMPDIR},100M,1K \
        HALT,${DL_DIR},100M,1K \
        HALT,${SSTATE_DIR},100M,1K \
        HALT,/tmp,10M,1K"
    
    #
    # Shared-state files from other locations
    #
    # As mentioned above, shared state files are prebuilt cache data objects which can be
    # used to accelerate build time. This variable can be used to configure the system
    # to search other mirror locations for these objects before it builds the data itself.
    #
    # This can be a filesystem directory, or a remote url such as https or ftp. These
    # would contain the sstate-cache results from previous builds (possibly from other
    # machines). This variable works like fetcher MIRRORS/PREMIRRORS and points to the
    # cache locations to check for the shared objects.
    # NOTE: if the mirror uses the same structure as SSTATE_DIR, you need to add PATH
    # at the end as shown in the examples below. This will be substituted with the
    # correct path within the directory structure.
    #SSTATE_MIRRORS ?= "\
    #file://.* https://someserver.tld/share/sstate/PATH;downloadfilename=PATH \
    #file://.* file:///some/local/dir/sstate/PATH"
    
    #
    # Yocto Project SState Mirror
    #
    # The Yocto Project has prebuilt artefacts available for its releases, you can enable
    # use of these by uncommenting the following lines. This will mean the build uses
    # the network to check for artefacts at the start of builds, which does slow it down
    # equally, it will also speed up the builds by not having to build things if they are
    # present in the cache. It assumes you can download something faster than you can build it
    # which will depend on your network.
    # Note: For this to work you also need hash-equivalence passthrough to the matching server
    #
    #BB_HASHSERVE_UPSTREAM = "typhoon.yocto.io:8687"
    #SSTATE_MIRRORS ?= "file://.* http://sstate.yoctoproject.org/all/PATH;downloadfilename=PATH"
    
    #
    # Qemu configuration
    #
    # By default native qemu will build with a builtin VNC server where graphical output can be
    # seen. The line below enables the SDL UI frontend too.
    PACKAGECONFIG:append:pn-qemu-system-native = " sdl"
    # By default libsdl2-native will be built, if you want to use your host's libSDL instead of 
    # the minimal libsdl built by libsdl2-native then uncomment the ASSUME_PROVIDED line below.
    #ASSUME_PROVIDED += "libsdl2-native"
    
    # You can also enable the Gtk UI frontend, which takes somewhat longer to build, but adds
    # a handy set of menus for controlling the emulator.
    #PACKAGECONFIG:append:pn-qemu-system-native = " gtk+"
    
    #
    # Hash Equivalence
    #
    # Enable support for automatically running a local hash equivalence server and
    # instruct bitbake to use a hash equivalence aware signature generator. Hash
    # equivalence improves reuse of sstate by detecting when a given sstate
    # artifact can be reused as equivalent, even if the current task hash doesn't
    # match the one that generated the artifact.
    #
    # A shared hash equivalent server can be set with "<HOSTNAME>:<PORT>" format
    #
    #BB_HASHSERVE = "auto"
    #BB_SIGNATURE_HANDLER = "OEEquivHash"
    
    #
    # Memory Resident Bitbake
    #
    # Bitbake's server component can stay in memory after the UI for the current command
    # has completed. This means subsequent commands can run faster since there is no need
    # for bitbake to reload cache files and so on. Number is in seconds, after which the
    # server will shut down.
    #
    #BB_SERVER_TIMEOUT = "60"
    
    # CONF_VERSION is increased each time build/conf/ changes incompatibly and is used to
    # track the version of this file when it was generated. This can safely be ignored if
    # this doesn't mean anything to you.
    CONF_VERSION = "2"
    ENABLE_UART = "1"
    RPI_USE_U_BOOT = "1"
    ```
  </details>
    
## 9. Build

you can type this to check bitbake

```jsx
cd ~/yocto/kirkstone/meta-raspberrypi
ls recipes-*/images
I use this at this time
bitbake rpi-test-image
```

```jsx
cd ~/yocto/kirkstone/builds/rpi
bitbake core-image-minimal
```

Now, you can see building.
<img src="image/bitbake.png" alt="Logo" width="800" height="300">
  </a><br>
I don’t know the accurate time to build, but I’m sure it will takes more than 1 hour.

So take a meal / rest / play game/ or do something.


## 10. Write your OS in SD card.

```jsx
sudo fdisk -l
```

```jsx
umount /dev/sda1
```

go to the directory where os is builded, unzip the os

```jsx
sudo bzip2 -dk core-image-minimal-raspberrypi4-20221025172232.rootfs.wic.bz2
```

```jsx
sudo dd if=core-image-minimal-raspberrypi4.wic.bz2 of=/dev/sda1
sync
```
<br><br><br>




# How to add seameconv

1. install dependencies(meta-seame) from here [meta-seame](https://github.com/chbae/meta-seame)  

2. Download meta-seameconv
   ```sh
   git clone https://github.com/jun-yub-kim/meta-seameconv.git
   ```
3. add this line in `meta-seame/classes/seame_image.bbclass`
   ```
   packagegrou-seame-devel \
   ```
4. build
   ```
   bitbake seame-image
   ```
