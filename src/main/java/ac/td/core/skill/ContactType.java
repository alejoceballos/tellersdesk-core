package ac.td.core.skill;

public enum ContactType {
    RARE_BOOK_DEALER(SkillType.ACADEMICS),
    LAW_PROFESSOR(SkillType.ACADEMICS),
    HEAD_LIBRARIAN(SkillType.ACADEMICS);

    private final SkillType skill;

    ContactType(SkillType skill) {
        this.skill = skill;
    }

    public SkillType getSkill() {
        return skill;
    }
}
