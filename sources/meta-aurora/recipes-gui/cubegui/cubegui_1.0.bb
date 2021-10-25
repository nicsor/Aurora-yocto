DESCRiPTION = "Cube gui"
LICENSE = " "
LIC_FILES_CHKSUM = " "
SRCREV = "81a0c5c5cbb6d2353ae0812313368befb16aa361"
SRC_URI = "git://git@github.com:/nicsor/Aurora-CubeApp.git;protocol=ssh;branch=vlad"

DEPENDS += "boost"
DEPENDS += "gtk+3"
#RDEPENDS_${PN} = "gtk+3"

S = "${WORKDIR}/git"

OE_CMAKE_GENERATOR = "Unix Makefiles"
EXTRA_OECMAKE = "-DENABLE_TESTING=OFF"
inherit pkgconfig cmake

