package com.zch.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by zch on 2022-04-23.
 */
public class JiaguTask extends DefaultTask {

    private final File apk;
    private final JiaguBean jiaguBean;

    @Inject
    public JiaguTask(File apk, JiaguBean jiaguBean) {
        // 依赖项目右侧的 Tasks 会创建一个 "jiagu" 分组
        setGroup("jiagu");
        this.apk = apk;
        this.jiaguBean = jiaguBean;
    }

    /**
     * 方法名随便，但一定要有这个注解
     */
    @TaskAction
    public void exec() {
        System.out.println(jiaguBean.toString());

        String jiaguToolDir = getProject().getRootDir() + "\\plugin\\360jiagubao";
        String jarFilePath = new File(jiaguToolDir + "\\jiagu.jar").getAbsolutePath();

        getProject().exec(execSpec -> {
            execSpec.setWorkingDir(jiaguToolDir);
            // 登录
            execSpec.commandLine("java", "-jar", jarFilePath,
                    "-login", jiaguBean.getUserName(), jiaguBean.getUserPwd());

            // 签名
            execSpec.commandLine("java", "-jar", jarFilePath,
                    "-importsign", jiaguBean.getKeyStorePath(), jiaguBean.getKeyStorePass(),
                    jiaguBean.getKeyStoreKeyAlias(), jiaguBean.getKeyStoreKeyAliasPwd());

            // 加固
            execSpec.commandLine("java", "-jar", jarFilePath,
                    "-jiagu", apk.getAbsolutePath(), apk.getParent(), "-autosign");
        });
    }
}
