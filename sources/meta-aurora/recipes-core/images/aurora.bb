SUMMARY = "Custom image for Aurora led cube"

IMAGE_FEATURES += "splash ssh-server-openssh x11-base"
DISTRO_FEATURES_append = " x11"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    kea \
    nano \
    sarpe \
    cubegui \
    gtk+3 \
    libgpiod \
    libgpiod-tools \
    "

inherit core-image 
