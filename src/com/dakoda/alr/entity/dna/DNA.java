package com.dakoda.alr.entity.dna;

public class DNA {

    private final Race race;
    private String skinOrFurColour = null;
    private String eyeColour = null;
    private Gender gender = null;

    private DNA(Race race) {
        this.race = race;
    }

    public Race getRace() {
        return race;
    }

    public String getSkinOrFurColour() {
        return skinOrFurColour;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public Gender getGender() {
        return gender;
    }


    public static final class Builder {
        private Race race;
        private String skinOrFurColour;
        private String eyeColour;
        private Gender gender;

        private Builder() {

        }

        public Builder race(Race race) {
            this.race = race;
            return this;
        }

        public Builder skinOrFurColour(String skinOrFurColour) {
            this.skinOrFurColour = skinOrFurColour;
            return this;
        }

        public Builder eyeColour(String eyeColour) {
            this.eyeColour = eyeColour;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public DNA build() {
            DNA dna = new DNA(this.race);
            dna.skinOrFurColour = this.skinOrFurColour;
            dna.eyeColour = this.eyeColour;
            dna.gender = this.gender;
            return dna;
        }
    }
}