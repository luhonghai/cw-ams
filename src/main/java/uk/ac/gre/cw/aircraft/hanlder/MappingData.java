package uk.ac.gre.cw.aircraft.hanlder;

import java.util.ArrayList;
import java.util.Collection;

public class MappingData {

    private int id;

    private Collection<Mapping> mappings;

    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(Collection<Mapping> mappings) {
        this.mappings = mappings;
    }

    public boolean isContainMapping(int mappingId) {
        if (mappings == null) mappings = new ArrayList<Mapping>();
        return mappings.contains(new Mapping(mappingId, true));
    }

    public void addMapping(Mapping mapping) {
        if (mappings == null) mappings = new ArrayList<Mapping>();
        mappings.add(mapping);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Mapping {
        private int id;
        private String name;
        private boolean mapped;

        public Mapping() {

        }

        public Mapping(int id, boolean mapped) {
            this.id = id;
            this.mapped = mapped;
        }

        public Mapping(int id,String name, boolean mapped) {
            this.id = id;
            this.name = name;
            this.mapped = mapped;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Mapping) {
                return this.id == ((Mapping) obj).id;
            }
            return super.equals(obj);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isMapped() {
            return mapped;
        }

        public void setMapped(boolean mapped) {
            this.mapped = mapped;
        }

    }
}
