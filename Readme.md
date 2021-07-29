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

## Build
`. sources/poky/oe-init-build-env`
`bitbake Aurora`

## Flash
Built images may be found in `${BUILDDIR}tmp/deploy/images/raspberrypi4/`

Extract the new image:
`bzip2 -df Aurora.wic.bz2`

Find the coresponding device for the sdcard using:
`lsblk -p`

Transfer the image to the sd-card using dd(Note: your of might be different. Pay close attention to the previous step.):
`dd if=Aurora.wic of=/dev/mmcblk0 bs=4M conv=fsync status=progress`
