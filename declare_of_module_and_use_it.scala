//模块的申明与调用
package first

import chisel3._
import chisel3.util._

class Add extends Module 
{
    val io = IO(new Bundle
    {
        val x = Input(UInt(16.W))
        val y = Input(UInt(16.W))
        val z = Output(UInt(16.W))
    })

    io.z := io.x + io.y

}

class first extends Module
{
    val io = IO(new Bundle
    {
        val a = Input(UInt(16.W))
        val b = Input(UInt(16.W))
        val enable = Input(UInt(1.W))
        val y = Output(UInt(16.W))
    })

    val result = Wire(UInt(16.W))
    val add = Module(new Add())
    add.io.x := io.a 
    add.io.y := io.b 
    result := add.io.z
    when (io.enable === 1.U(1.W))
    {
        io.y := result
    }
    .otherwise
    {
        io.y := 0.U(16.W)
    }
}

object first extends App {
  (new chisel3.stage.ChiselStage).emitVerilog(new first())
}