package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.COMPOSURE, AttributeType.WITS },
        categories = { CategoryType.MENTAL, CategoryType.SOCIAL },
        type = ActionType.REFLEXIVE
)
class PerceptionTest extends AttributeTaskTest<Perception> {
}
