DESCRiPTION = "Cube gui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
SRCREV = "6797b4abebc6a4ffc533593bfdd90a3a668e25b7"
SRC_URI = "git://git@github.com:/nicsor/Aurora-CubeApp.git;protocol=ssh;branch=main"

DEPENDS += "boost"
DEPENDS += "gtk+3"
DEPENDS += "protobuf protobuf-native"
DEPENDS += "libgcrypt"

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
