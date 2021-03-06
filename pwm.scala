//pwm波示例

import chisel3._
import chisel3.util._

object pwm extends App 
{
  (new chisel3.stage.ChiselStage).emitVerilog(new pwm())
}

class pwm extends Module
{
    val io = IO(new Bundle
    {
        val din = Input(UInt(10.W))       //宽度也可改
        val dout = Output(UInt(1.W))
    })
    def PWM(nrCycles:Int,din:UInt) =      
    {
        val cntReg = RegInit(0.U(unsignedBitLength(nrCycles-1).W))
        //val cntReg = RegInit(0.U(unsignedBitLength(nrCycles-1.U).W))
        cntReg := Mux(cntReg === (nrCycles - 1).U,0.U,cntReg + 1.U)
        din > cntReg
    }
    io.dout := PWM(100,io.din)   //100可改小
}