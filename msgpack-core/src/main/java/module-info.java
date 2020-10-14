/**
 *
 */

module org.msgpack.msgpack.core {
    //requires +++;

    //because of import sun.misc.Unsafe;
    requires jdk.unsupported;


    exports org.msgpack.core;
    exports org.msgpack.core.buffer;
    exports org.msgpack.core.annotations;

    exports org.msgpack.value;
    exports org.msgpack.value.impl;

}