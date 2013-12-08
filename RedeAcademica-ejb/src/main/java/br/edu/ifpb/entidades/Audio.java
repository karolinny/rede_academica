
package br.edu.ifpb.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ad")
public class Audio extends Multimidia {
    
    private String Compositor;
    private String Album;

    public Audio() {
    }

    public String getCompositor() {
        return Compositor;
    }

    public void setCompositor(String Compositor) {
        this.Compositor = Compositor;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }    
}
