cc_binary(
    name = "multi_thread",
    srcs = ["multi_thread.cc"],
    linkopts = ['-pthread'],
)

cc_binary(
    name = "multi_thread_mutex",
    srcs = ["multi_thread_mutex.cc"],
    linkopts = ['-pthread'],
)

cc_binary(
    name = "multi_thread_cas",
    srcs = ["multi_thread_cas.cc"],
    linkopts = ['-pthread'],
)

cc_binary(
    name = "basic_add",
    srcs = ["basic_add.cc"],
    linkopts = ['-pthread'],
)


java_binary(
    name = "basic_add_java",
    srcs = [
        "BasicAdd.java",
    ],
    main_class = "BasicAdd",
)

java_binary(
    name = "MultiThreadJava",
    srcs = [
    	 "MultiThread.java",
    ],
    main_class = "MultiThread",
)

java_binary(
    name = "MultiThreadMutexJava",
    srcs = [
        "MultiThreadMutex.java",
    ],
    main_class = "MultiThreadMutex",
)

java_binary(
    name = "MultiThreadCasJava",
    srcs = [
        "MultiThreadCas.java",
    ],
    main_class = "MultiThreadCas",
)