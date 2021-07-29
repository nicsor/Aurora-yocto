LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI += "git://github.com/simogasp/cmake-examples"
SRCREV = "b56550aeaa9635fd0daf22ecd2fec516e33dcdf1"

S = "${WORKDIR}/git/libFoo"

inherit pkgconfig cmake
