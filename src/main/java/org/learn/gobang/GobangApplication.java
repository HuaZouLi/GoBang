package org.learn.gobang;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.GUIState;
import org.learn.gobang.view.IndexView;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GobangApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {

        launch(GobangApplication.class, IndexView.class,args);

    }

}
