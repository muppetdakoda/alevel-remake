package com.dakoda.alr.loc;
import com.dakoda.alr.entity.hostile.EntityHostile;
import com.dakoda.alr.entity.npc.NPC;
import java.util.ArrayList;

public class Location {

    private String name;
    private boolean restable;
    private boolean encounterable;
    private int levelScale;
    private LocationType locationType;
    private ArrayList<EntityHostile> hostileArrayList;
    private ArrayList<NPC> npcArrayList;
    private ArrayList<Location> subLocationArrayList;
    private ArrayList<Location> linkedLocationArrayList;

    public static void linkLocations(Location loc1, Location loc2) {
        loc1.addLinkedLocation(loc2);
        loc2.addLinkedLocation(loc1);
    }

    private void addLinkedLocation(Location locationToLink) {
        linkedLocationArrayList.add(locationToLink);
    }

    public ArrayList<Location> getLinkedLocations() {
        return linkedLocationArrayList;
    }

    public String getName() {
        return name;
    }

    public boolean isRestable() {
        return restable;
    }

    public int getLevelScale() {
        return levelScale;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public ArrayList<EntityHostile> getHostiles() {
        return hostileArrayList;
    }

    public ArrayList<NPC> getNPCs() {
        return npcArrayList;
    }

    public ArrayList<Location> getSubLocations() {
        return subLocationArrayList;
    }

    public boolean isEncounterable() {
        return encounterable;
    }

    public class LocationBuilder {

        private String name;
        private boolean restable;
        private boolean encounterable;
        private int levelScale;
        private LocationType locationType;
        private ArrayList<EntityHostile> hostileArrayList;
        private ArrayList<NPC> npcArrayList;
        private ArrayList<Location> subLocationArrayList;

        private void initHostileList() {
            hostileArrayList = new ArrayList<>();
        }

        private void initNPCList() {
            npcArrayList = new ArrayList<>();
        }

        private void initSubLocList() {
            subLocationArrayList = new ArrayList<>();
        }

        public LocationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        private void setIsRestable(boolean restable) {
            this.restable = restable;
        }

        private void setIsEncounterable(boolean encounterable) {
            this.encounterable = encounterable;
        }

        public LocationBuilder setLevelScale(int levelScale) {
            this.levelScale = levelScale;
            return this;
        }

        public LocationBuilder setLocationType(LocationType locationType) {
            this.locationType = locationType;
            setIsRestable(locationType.isRestable());
            setIsEncounterable(locationType.isEncounterable());
            return this;
        }

        public LocationBuilder addHostileEncounter(EntityHostile entityHostile) {
            if (hostileArrayList == null) { initHostileList(); }
            this.hostileArrayList.add(entityHostile);
            return this;
        }

        public LocationBuilder addNPC(NPC npc) {
            if (npc == null) { initNPCList(); }
            this.npcArrayList.add(npc);
            return this;
        }

        public LocationBuilder addSubLocation(Location location) {
            if (subLocationArrayList == null) { initSubLocList(); }
            this.subLocationArrayList.add(location);
            return this;
        }

        public Location build() {
            Location location = new Location();
            location.name = this.name;
            location.restable = this.restable;
            location.encounterable = this.encounterable;
            location.locationType = locationType;
            location.levelScale = this.levelScale;
            location.hostileArrayList = this.hostileArrayList;
            location.npcArrayList = this.npcArrayList;
            location.subLocationArrayList = this.subLocationArrayList;
            location.linkedLocationArrayList = new ArrayList<>();
            if (checkValidImplementation(location)) {
                return location;
            } else {
                throw new InvalidLocationDefinitionException("You defined an impossible location.");
            }
        }

        private boolean checkValidImplementation(Location location) {
            return location.encounterable == (location.hostileArrayList != null);
        }
    }
}
