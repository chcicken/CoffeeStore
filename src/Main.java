import GUI.FrameLogin;
import GUI.common.Language;
import GUI.common.Theme;
import Utils.FileHandler;
import Utils.General;
import Utils.SystemConstant;

import java.awt.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties config = FileHandler.importConfig(SystemConstant.CONFIG_FILE_URL);
        General.importMapper(config);
        Language.setup();
        Theme.setupDefault();
        Theme.setupComponentStyle();

        General.frame = new FrameLogin();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                General.frame.setVisible(true);
                General.frame.requestFocusInWindow();
            }
        });
    }
}
