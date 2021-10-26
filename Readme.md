# Aurora - RaspberyPi image
## Setup environment

```
 sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
     build-essential chrpath socat cpio python3 python3-pip python3-pexpect \
     xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
     pylint3 xterm
```

## Initialize project
`./init`

## Build for Raspberry Pi 4
`. sources/poky/oe-init-build-env`
`bitbake Aurora`

## Build for QEMU(x86_64)
Change the `MACHINE` variable to `qemux86-64` in `${BUILDDIR}/conf/local.conf` \
You might need to comment all the contents in `sources/meta-aurora/recipes-core/init-ifupdown/init-ifupdown_1.0.bbappend` \
Run `sources/poky/oe-init-build-env` `bitbake Aurora`

If you encounter any error at running the task `do_package_write_rpm` you can set the `PACKAGE_CLASSES` variable in `${BUILDDIR}/conf/local.conf` to `package_deb`


## Flash
Built images may be found in `${BUILDDIR}tmp/deploy/images/raspberrypi4/`

Extract the new image:
`bzip2 -df Aurora.wic.bz2`

Find the coresponding device for the sdcard using:
`lsblk -p`

Transfer the image to the sd-card using dd(Note: your of might be different. Pay close attention to the previous step.):
`dd if=Aurora.wic of=/dev/mmcblk0 bs=4M conv=fsync status=progress`
