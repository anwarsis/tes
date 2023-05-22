/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import javax.print.attribute.standard.Media;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author sim
 */
public class upload {
 Media media;

    @Command
    public void doUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();
        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }
        if (upEvent != null) {
            media = (Media) upEvent.getMedia();
            Messagebox.show("File Uploaded: " + media.getName());

        }
    }

    @Command
    public void doDownload() {
        if (media != null)
            Filedownload.save((org.zkoss.util.media.Media) media);
        else
            Messagebox.show("First Drop Your File");

    }
}
 