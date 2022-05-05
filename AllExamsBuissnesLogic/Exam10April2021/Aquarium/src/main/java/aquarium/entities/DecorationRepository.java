package aquarium.entities;

import aquarium.entities.decorations.Decoration;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


public class DecorationRepository implements Repository {
    private Collection<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    @Override
    public void add(Decoration decoration) {
this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {

        return this.decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        for (Decoration decoration :this.decorations) {
            if (type.equals(decoration.getClass().getSimpleName())){
                return decoration;
            }
        }
        return null;
    }

    public Collection<Decoration> getDecorations() {
        return this.decorations.stream().collect(Collectors.toUnmodifiableList());
    }


}
