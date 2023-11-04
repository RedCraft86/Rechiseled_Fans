package net.redcraft86.rechiseled_fans;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class FanBlockBase extends Block
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final BooleanProperty INVERTED = BooleanProperty.create("inverted");

    public FanBlockBase()
    {
        super(BlockBehaviour.Properties
                .copy(Blocks.IRON_BLOCK)
                .sound(FanBlockSounds.FAN_BLOCKS));

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ON, false)
                .setValue(INVERTED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> Builder)
    {
        Builder.add(FACING);
        Builder.add(ON);
        Builder.add(INVERTED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext Context)
    {
        return this.defaultBlockState()
                .setValue(FACING, Context.getHorizontalDirection().getOpposite())
                .setValue(ON, Context.getLevel().hasNeighborSignal(Context.getClickedPos()))
                .setValue(INVERTED, false);
    }

    private void UpdateRedstoneState(Level InLevel, BlockPos Pos, BlockState State)
    {
        boolean TargetState = State.getValue(INVERTED) != InLevel.hasNeighborSignal(Pos);
        InLevel.setBlock(Pos, State.setValue(ON, TargetState), 2);
    }

    @Override
    public void onPlace(BlockState State, Level InLevel, BlockPos Pos, BlockState OldState, boolean Moving)
    {
        this.UpdateRedstoneState(InLevel, Pos, State);
    }

    @Override
    public void neighborChanged(BlockState State, Level InLevel, BlockPos Pos, Block InBlock, BlockPos FromPos, boolean Moving)
    {
        this.UpdateRedstoneState(InLevel, Pos, State);
    }

    @Override
    public InteractionResult use(BlockState State, Level InLevel, BlockPos Pos, Player InPlayer, InteractionHand Hand, BlockHitResult Hit)
    {
        if (!InPlayer.isCrouching())
        {
            return InteractionResult.PASS;
        }
        else if (InLevel.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }
        else
        {
            State = State.cycle(INVERTED);
            this.UpdateRedstoneState(InLevel, Pos, State);
            InPlayer.displayClientMessage(Component.literal("Redstone mode inverted!"), true);
            return InteractionResult.CONSUME;
        }
    }
}
