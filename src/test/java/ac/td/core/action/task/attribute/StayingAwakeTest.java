package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;

@AttributeMetadata(
        drivingAttributes = { AttributeType.RESOLVE, AttributeType.STAMINA },
        categories = CategoryType.MENTAL,
        type = ActionType.INSTANT
)
class StayingAwakeTest extends AttributeTaskTest<StayingAwake> {
}
