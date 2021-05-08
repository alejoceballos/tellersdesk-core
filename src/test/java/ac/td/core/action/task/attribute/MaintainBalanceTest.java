package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.DEXTERITY, AttributeType.COMPOSURE },
        categories = CategoryType.PHYSICAL,
        type = ActionType.REFLEXIVE
)
class MaintainBalanceTest extends AttributeTaskTest<MaintainBalance> {
}
