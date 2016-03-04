package com.liveramp.daemon_lib;

import java.io.IOException;

import com.liveramp.daemon_lib.builders.BlockingDaemonBuilder;
import com.liveramp.daemon_lib.builders.ForkingDaemonBuilder;
import com.liveramp.daemon_lib.builders.ThreadingDaemonBuilder;
import com.liveramp.daemon_lib.executors.forking.ProcessJobletRunner;
import com.liveramp.daemon_lib.utils.JobletConfigJavaDeserializer;
import com.liveramp.daemon_lib.utils.JobletConfigJavaSerializer;

public class DaemonBuilders {

  public static <T extends JobletConfig> ForkingDaemonBuilder<T> forked(String workingDir, String identifier, Class<? extends JobletFactory<T>> jobletFactoryClass, JobletConfigProducer<T> jobletConfigProducer) throws IllegalAccessException, IOException, InstantiationException {
    return new ForkingDaemonBuilder<>(
        workingDir,
        identifier,
        jobletFactoryClass,
        jobletConfigProducer,
        null,
        new JobletConfigJavaSerializer<T>(),
        new JobletConfigJavaDeserializer<T>()
    );
  }

  public static <T extends JobletConfig> ForkingDaemonBuilder<T> forked(String workingDir, String identifier, Class<? extends JobletFactory<T>> jobletFactoryClass, JobletConfigProducer<T> jobletConfigProducer, ProcessJobletRunner jobletRunner) throws IllegalAccessException, IOException, InstantiationException {
    return new ForkingDaemonBuilder<>(
        workingDir,
        identifier,
        jobletFactoryClass,
        jobletConfigProducer,
        jobletRunner,
        new JobletConfigJavaSerializer<T>(),
        new JobletConfigJavaDeserializer<T>()
    );
  }

  public static <T extends JobletConfig> BlockingDaemonBuilder<T> blocking(String identifier, JobletFactory<T> jobletFactory, JobletConfigProducer<T> jobletConfigProducer) throws InstantiationException, IllegalAccessException {
    return new BlockingDaemonBuilder<>(
        identifier,
        jobletFactory,
        jobletConfigProducer);
  }

  public static <T extends JobletConfig> ThreadingDaemonBuilder<T> threaded(String identifier, JobletFactory<T> jobletFactory, JobletConfigProducer<T> jobletConfigProducer) throws IllegalAccessException, IOException, InstantiationException {
    return new ThreadingDaemonBuilder<>(
        identifier,
        jobletFactory,
        jobletConfigProducer
    );
  }
}
