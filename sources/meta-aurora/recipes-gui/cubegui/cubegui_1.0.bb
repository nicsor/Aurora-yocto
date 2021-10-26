DESCRiPTION = "Cube gui"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = " "
SRCREV = "6d3b7ad09fbcbd83b13c432f43e63861ce189436"
SRC_URI = "git://git@github.com:/nicsor/Aurora-CubeApp.git;protocol=ssh;branch=vlad"

DEPENDS += "boost"
DEPENDS += "gtk+3"
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
