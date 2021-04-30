package ac.td.core.character;

import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

import java.util.Set;

public interface SkillfulCharacter {
    int getAttribute(final AttributeType attribute);

    int getSkill(final SkillType skill);

    Set<SpecialtyType> getSpecialties();
}
