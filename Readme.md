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
`bitbake aurora`

## Build for QEMU(x86_64)
Change the `MACHINE` variable to `qemux86-64` in `${BUILDDIR}/conf/local.conf` \
Run `sources/poky/oe-init-build-env` `bitbake aurora`

If you encounter any error at running the task `do_package_write_rpm` you can set the `PACKAGE_CLASSES` variable in `${BUILDDIR}/conf/local.conf` to `package_deb`

To speed up the qemu image, one can enable virtualization: https://help.ubuntu.com/community/KVM/Installation
```
sudo apt-get install qemu-kvm libvirt-daemon-system libvirt-clients bridge-utils
sudo adduser `id -un` libvirt
sudo adduser `id -un` kvm
log out/reboot
```
After that, one can tweak the parameters of the qemu virtual machine so that it has access to more memory/processing power/cpu features". For example:
`runqemu qemux86-64 qemuparams="-cpu max -smp $(nproc) -m 2048M -display sdl -machine type=q35,accel=kvm"`
For a bigger list of qemu features and other parameters, check: https://wiki.gentoo.org/wiki/QEMU/Options


## Flash
Built images may be found in `${BUILDDIR}tmp/deploy/images/raspberrypi4/`

Extract the new image:
`bzip2 -df aurora.wic.bz2`

Find the coresponding device for the sdcard using:
`lsblk -p`

Transfer the image to the sd-card using dd(Note: your of might be different. Pay close attention to the previous step.):
`dd if=aurora.wic of=/dev/mmcblk0 bs=4M conv=fsync status=progress`
