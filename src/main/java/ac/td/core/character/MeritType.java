package ac.td.core.character;

import ac.td.core.skill.SkillType;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public enum MeritType {
    AREA_OF_EXPERTISE(new Integer[] { 1 }, Map.of(AttributeType.RESOLVE.name(), 2), null),
    COMMON_SENSE(new Integer[] { 3 }),
    DANGER_SENSE(new Integer[] { 2 }),
    DIRECTION_SENSE(new Integer[] { 1 }),
    EIDETIC_MEMORY(new Integer[] { 2 }),
    ENCYCLOPEDIC_KNOWLEDGE(new Integer[] { 2 }),
    EYE_FOR_THE_STRANGE(
            new Integer[] { 2 },
            Map.of(AttributeType.RESOLVE.name(), 2, SkillType.OCCULT.name(), 1),
            null),
    FAST_REFLEXES(
            new Integer[] { 1, 2, 3 },
            null,
            Map.of(AttributeType.WITS.name(), 3, AttributeType.DEXTERITY.name(), 3)),
    GOOD_TIME_MANAGEMENT(
            new Integer[] { 1 },
            null,
            Map.of(SkillType.ACADEMICS.name(), 2, SkillType.SCIENCE.name(), 2)),
    HOLISTIC_AWARENESS(new Integer[] { 1 }),
    INDOMITABLE(new Integer[] { 2 }, Map.of(AttributeType.RESOLVE.name(), 3), null),

    ;

    private Integer[] dotsRange;
    private Map<String, Integer> requiredPrerequisites;
    private Map<String, Integer> optionalPrerequisites;
    private Set<String> forbiddenPrerequisites;
    private boolean specialtyNeeded;


    MeritType(final Integer[] dotsRange) {
        this.dotsRange = dotsRange;
    }

    MeritType(
            final Integer[] dotsRange,
            final Map<String, Integer> requiredPrerequisites,
            final Map<String, Integer> optionalPrerequisites) {
        this.dotsRange = dotsRange;
        if (!Objects.isNull(requiredPrerequisites)) this.requiredPrerequisites = requiredPrerequisites;
        if (!Objects.isNull(optionalPrerequisites)) this.optionalPrerequisites = optionalPrerequisites;
    }

    public Integer[] getDotsRange() {
        return dotsRange;
    }

    public Map<String, Integer> getRequiredPrerequisites() {
        return Map.copyOf(this.requiredPrerequisites);
    }
}
