package com.enjoy.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

public class JiaguPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        // 我们的插件可以让使用者配置 JiaguBean 中的参数
        final JiaguBean jiaguBean = project.getExtensions().create("jiagu", JiaguBean.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                // 得到待加固的 APK
                // 相当 getByName("android")
                AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
                // 得到一个集合 [debug,release]
                appExtension.getApplicationVariants().all(new Action<ApplicationVariant>() {
                    @Override
                    public void execute(ApplicationVariant applicationVariant) {
                        applicationVariant.getOutputs().all(new Action<BaseVariantOutput>() {
                            @Override
                            public void execute(BaseVariantOutput baseVariantOutput) {
                                // 得到需要加固的 debug apk、release apk

                                if("release".equals(baseVariantOutput.getName())){
                                    File outputFile = baseVariantOutput.getOutputFile();

//                                    System.out.println("getPath="+outputFile.getAbsoluteFile().getPath());
//                                    System.out.println("outputFile="+outputFile.getName());
//                                    System.out.println("baseVariantOutput="+baseVariantOutput.getName());

                                    project.getTasks().create("jiagu" + baseVariantOutput.getName(), JiaguTask.class, outputFile, jiaguBean);
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}