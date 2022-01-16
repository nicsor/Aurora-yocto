DESCRiPTION = "Cube gui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
SRCREV = "d8f68d573b0bbc699e07bbabef684df7e054a564"
SRC_URI = "git://git@github.com:/nicsor/BreadCrumbs.git;protocol=ssh;branch=master"

DEPENDS += "boost"
DEPENDS += "gtk+3"
DEPENDS += "protobuf protobuf-native"
DEPENDS += "libgcrypt"
DEPENDS += "libgpiod"

#OpenGL app dependencies
DEPENDS += "glfw freeglut"
#RDEPENDS_${PN} = "gtk+3"

S = "${WORKDIR}/git"

OE_CMAKE_GENERATOR = "Unix Makefiles"
EXTRA_OECMAKE = "-DENABLE_TESTING=OFF"
inherit pkgconfig cmake 

SRC_URI += "file://gui_entry.desktop"

do_install_append() {
    mkdir -p ${D}${sysconfdir}/xdg/autostart
    install -m 0744 ${WORKDIR}/gui_entry.desktop ${D}${sysconfdir}/xdg/autostart/aurora.desktop
}
