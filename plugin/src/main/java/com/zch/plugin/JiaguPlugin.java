package com.zch.plugin;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

public class JiaguPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        // 我们的插件可以让使用者配置 JiaguBean 中的参数
        final JiaguBean jiaguBean = project.getExtensions().create("jiagu", JiaguBean.class);
        project.afterEvaluate(project1 -> {
            // 得到待加固的 APK
            // 相当 getByName("android")
            AppExtension appExtension = project1.getExtensions().getByType(AppExtension.class);
            // 得到一个集合 [debug,release]
            appExtension.getApplicationVariants().all(applicationVariant -> applicationVariant.getOutputs().all(baseVariantOutput -> {
                // 得到需要加固的 debug apk、release apk
                if ("release".equals(baseVariantOutput.getName())) {
                    File outputFile = baseVariantOutput.getOutputFile();

//                        System.out.println("getPath=" + outputFile.getAbsoluteFile().getPath());
//                        System.out.println("outputFile=" + outputFile.getName());
//                        System.out.println("baseVariantOutput="+baseVariantOutput.getName());

                    project1.getTasks().create("jiagu" + baseVariantOutput.getName(), JiaguTask.class, outputFile, jiaguBean);
                }
            }));
        });
    }
}