package ac.td.core.skill;

public enum SpecialtyType {
    NOT_APPLICABLE(SkillType.NOT_APPLICABLE),
    // MENTAL
    //   ACADEMICS
    ENGLISH_LITERATURE(SkillType.ACADEMICS),
    HISTORY(SkillType.ACADEMICS),
    LAW(SkillType.ACADEMICS),
    LINGUISTICS(SkillType.ACADEMICS),
    RESEARCH(SkillType.ACADEMICS),

    // PHYSICAL
    EVASION(SkillType.DRIVE),
    MOTORCYCLES(SkillType.DRIVE),
    PILOTING(SkillType.DRIVE),
    RACING(SkillType.DRIVE),
    NAVIGATING(SkillType.DRIVE), // Not original CoW
    STUNTS(SkillType.DRIVE);

    private final SkillType skill;

    SpecialtyType(SkillType skill) {
        this.skill = skill;
    }

    public SkillType getSkill() {
        return skill;
    }
}
