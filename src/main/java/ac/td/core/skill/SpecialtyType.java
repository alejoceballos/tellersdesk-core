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
    //   COMPUTER
    DATA_RETRIEVAL(SkillType.COMPUTER),
    DIGITAL_SECURITY(SkillType.COMPUTER),
    HACKING(SkillType.COMPUTER),
    PROGRAMMING(SkillType.COMPUTER),
    USER_INTERFACE_DESIGN(SkillType.COMPUTER),
    //   CRAFTS
    AUTOMOTIVE(SkillType.CRAFTS),
    CARPENTRY(SkillType.CRAFTS),
    JURY_RIGGING(SkillType.CRAFTS),
    SCULPTING(SkillType.CRAFTS),
    WELDING(SkillType.CRAFTS),
    //   INVESTIGATION
    CRIME_SCENES(SkillType.INVESTIGATION),
    CRYPTOGRAPHY(SkillType.INVESTIGATION),
    DREAMS(SkillType.INVESTIGATION),
    FORENSIC_ACCOUNTING(SkillType.INVESTIGATION),
    RIDDLES(SkillType.INVESTIGATION),

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
