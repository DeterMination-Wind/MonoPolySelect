package monopolyselect;

import arc.Core;
import arc.Events;
import arc.input.KeyBind;
import arc.input.KeyCode;
import mindustry.Vars;
import mindustry.content.UnitTypes;
import mindustry.game.EventType.Trigger;
import mindustry.gen.Unit;
import mindustry.input.InputHandler;
import mindustry.mod.Mod;
import mindustry.type.UnitType;

/**
 * MonoPolySelect — 指挥模式辅助小模组。
 *
 * 在「设置→控制」中注册两个可配置快捷键（默认未绑定，需自行设置）：
 *  - mps-select-mono：一键选中己方所有 mono
 *  - mps-select-poly：一键选中己方所有 poly
 *
 * 按下快捷键后会自动进入指挥模式，可直接右键对选中的单位下达命令。
 */
public class MonoPolySelectMod extends Mod{

    /** 快捷键：选中所有 mono（在 设置→控制 中绑定，默认未设置） */
    public static final KeyBind selectMono = KeyBind.add("mps-select-mono", KeyCode.unset, "command");

    /** 快捷键：选中所有 poly（在 设置→控制 中绑定，默认未设置） */
    public static final KeyBind selectPoly = KeyBind.add("mps-select-poly", KeyCode.unset, "command");

    public MonoPolySelectMod(){
        Events.run(Trigger.update, () -> {
            if(!Vars.state.isGame() || Vars.headless) return;
            // 对话框或输入框打开时不响应（例如正在改键时）
            if(Core.scene.hasDialog() || Core.scene.hasField()) return;
            if(Vars.player == null || Vars.player.dead()) return;

            if(Core.input.keyTap(selectMono)){
                selectAll(UnitTypes.mono);
            }

            if(Core.input.keyTap(selectPoly)){
                selectAll(UnitTypes.poly);
            }
        });
    }

    /** 选中己方所有指定类型的单位，并自动进入指挥模式以便直接下达命令 */
    private void selectAll(UnitType type){
        InputHandler input = Vars.control.input;
        if(input == null) return;

        // 自动开启指挥模式：按下快捷键后即可直接右键下达移动/攻击等命令
        input.commandMode = true;

        input.selectedUnits.clear();
        input.commandBuildings.clear();

        for(Unit unit : Vars.player.team().data().units){
            if(unit.isCommandable() && unit.type == type){
                input.selectedUnits.add(unit);
            }
        }

        // 通知 UI 刷新指挥栏
        Events.fire(Trigger.unitCommandChange);
    }
}
