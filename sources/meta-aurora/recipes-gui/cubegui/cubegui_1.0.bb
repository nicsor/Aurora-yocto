DESCRiPTION = "Cube gui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
SRCREV = "0e93ec6cc54166270b7cba01b35e51b97f8bb480"
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
inherit pkgconfig cmake update-rc.d systemd

SRC_URI += " \
    file://aurora.service.in \
    file://aurora.init \
"

do_install_append() {
    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aurora.init ${D}/${sysconfdir}/init.d/aurora

    install -d ${D}${systemd_unitdir}/system
    sed -e 's,%sbindir%,${sbindir},g' \
        < ${WORKDIR}/aurora.service.in \
        > ${D}${systemd_unitdir}/system/aurora.service
}

RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "aurora.service"

INITSCRIPT_NAME = "aurora"
INITSCRIPT_PARAMS = "start 99 5 . stop 20 0 1 2 3 6 ."

FILES_${PN} += " \
    ${systemd_unitdir} \
    ${sysconfdir} \
"
