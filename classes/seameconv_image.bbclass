inherit core-image

SEAMECONV_IMAGE_BASE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-seameconv-devel \
"

IMAGE_INSTALL = "${SEAME_CONV_IMAGE_BASE_INSTALL}"
