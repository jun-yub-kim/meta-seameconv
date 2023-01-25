SUMMARY = "Components useful to developers added to -devel images"
LICENSE = "CLOSED"

inherit packagegroup

RDEPENDS:${PN} = "\
    gcc \
    i2c-tools \
    nano \
    openssh \
    python3-setuptools \
    python3-pip \
"
