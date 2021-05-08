package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.STRENGTH, AttributeType.STAMINA },
        categories = CategoryType.PHYSICAL,
        type = ActionType.EXTENDED
)
class HoldTest extends AttributeTaskTest<Hold> {
}
