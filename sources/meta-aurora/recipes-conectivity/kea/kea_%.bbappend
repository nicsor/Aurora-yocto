FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:" 

SRC_URI += "file://kea-dhcp4.conf" 

do_install_append() { 
    install -m 644 ${WORKDIR}/kea-dhcp4.conf ${D}${sysconfdir}/kea/kea-dhcp4.conf
}
