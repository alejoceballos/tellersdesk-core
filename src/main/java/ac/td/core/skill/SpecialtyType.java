package ac.td.core.skill;

public enum SpecialtyType {
    DUMMY_1(SkillType.ACADEMICS),
    DUMMY_2(SkillType.ACADEMICS),
    DUMMY_3(SkillType.ACADEMICS),
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
