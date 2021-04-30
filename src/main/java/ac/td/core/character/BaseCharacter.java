package ac.td.core.character;

import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BaseCharacter implements Cloneable, SkillfulCharacter, AnchoredCharacter {
    @Getter protected String concept;
    protected VirtueType virtue;
    protected ViceType vice;
    protected int experiencePoints;
    protected final Map<String, Integer> traits = new HashMap<>(
            Map.ofEntries(
                    Map.entry(AttributeType.INTELLIGENCE.name(), 1),
                    Map.entry(AttributeType.WITS.name(), 1),
                    Map.entry(AttributeType.RESOLVE.name(), 1),
                    Map.entry(AttributeType.STRENGTH.name(), 1),
                    Map.entry(AttributeType.DEXTERITY.name(), 1),
                    Map.entry(AttributeType.STAMINA.name(), 1),
                    Map.entry(AttributeType.PRESENCE.name(), 1),
                    Map.entry(AttributeType.MANIPULATION.name(), 1),
                    Map.entry(AttributeType.COMPOSURE.name(), 1),
                    Map.entry(SkillType.ACADEMICS.name(), 0),
                    Map.entry(SkillType.COMPUTER.name(), 0),
                    Map.entry(SkillType.CRAFTS.name(), 0),
                    Map.entry(SkillType.INVESTIGATION.name(), 0),
                    Map.entry(SkillType.MEDICINE.name(), 0),
                    Map.entry(SkillType.OCCULT.name(), 0),
                    Map.entry(SkillType.POLITICS.name(), 0),
                    Map.entry(SkillType.SCIENCE.name(), 0),
                    Map.entry(SkillType.ATHLETICS.name(), 0),
                    Map.entry(SkillType.BRAWL.name(), 0),
                    Map.entry(SkillType.DRIVE.name(), 0),
                    Map.entry(SkillType.FIREARMS.name(), 0),
                    Map.entry(SkillType.LARCENY.name(), 0),
                    Map.entry(SkillType.STEALTH.name(), 0),
                    Map.entry(SkillType.SURVIVAL.name(), 0),
                    Map.entry(SkillType.WEAPONRY.name(), 0),
                    Map.entry(SkillType.ANIMAL_KEN.name(), 0),
                    Map.entry(SkillType.EMPATHY.name(), 0),
                    Map.entry(SkillType.EXPRESSION.name(), 0),
                    Map.entry(SkillType.INTIMIDATION.name(), 0),
                    Map.entry(SkillType.PERSUASION.name(), 0),
                    Map.entry(SkillType.SOCIALIZE.name(), 0),
                    Map.entry(SkillType.STREETWISE.name(), 0),
                    Map.entry(SkillType.SUBTERFUGE.name(), 0)));

    protected final Set<SpecialtyType> specialties = new HashSet<>();

    public BaseCharacter(final int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @Override
    public VirtueType getVirtue() {
        return virtue;
    }

    @Override
    public ViceType getVice() {
        return vice;
    }

    @Override
    public int getAttribute(final AttributeType attributeType) {
        return this.traits.get(attributeType.name());
    }

    @Override
    public int getSkill(final SkillType skillType) {
        return this.traits.get(skillType.name());
    }

    @Override
    public Set<SpecialtyType> getSpecialties() {
        return new HashSet<>(this.specialties);
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    protected int getLevel(final String trait) {
        return this.traits.get(trait);
    }
}
