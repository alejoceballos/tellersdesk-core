package ac.td.core.skill;

import ac.td.core.character.CategoryType;

public enum SkillType {
    NOT_APPLICABLE(CategoryType.NOT_APPLICABLE),
    // Mental
    ACADEMICS(CategoryType.MENTAL),
    COMPUTER(CategoryType.MENTAL),
    CRAFTS(CategoryType.MENTAL),
    INVESTIGATION(CategoryType.MENTAL),
    MEDICINE(CategoryType.MENTAL),
    OCCULT(CategoryType.MENTAL),
    POLITICS(CategoryType.MENTAL),
    SCIENCE(CategoryType.MENTAL),
    // Physical
    ATHLETICS(CategoryType.PHYSICAL),
    BRAWL(CategoryType.PHYSICAL),
    DRIVE(CategoryType.PHYSICAL),
    FIREARMS(CategoryType.PHYSICAL),
    LARCENY(CategoryType.PHYSICAL),
    STEALTH(CategoryType.PHYSICAL),
    SURVIVAL(CategoryType.PHYSICAL),
    WEAPONRY(CategoryType.PHYSICAL),
    // Social
    ANIMAL_KEN(CategoryType.SOCIAL),
    EMPATHY(CategoryType.SOCIAL),
    EXPRESSION(CategoryType.SOCIAL),
    INTIMIDATION(CategoryType.SOCIAL),
    PERSUASION(CategoryType.SOCIAL),
    SOCIALIZE(CategoryType.SOCIAL),
    STREETWISE(CategoryType.SOCIAL),
    SUBTERFUGE(CategoryType.SOCIAL);

    private final CategoryType category;

    SkillType(CategoryType category) {
        this.category = category;
    }

    public CategoryType getCategory() {
        return category;
    }

}
