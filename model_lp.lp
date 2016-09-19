Minimize
  Obj
Subject To
 constr1_0: Y_0_0 + Y_0_1 + Y_0_2 + Y_0_3 = 9
 constr1_1: Y_1_0 + Y_1_1 + Y_1_2 + Y_1_3 = 6
 constr1_2: Y_2_0 + Y_2_1 + Y_2_2 + Y_2_3 = 4
 constr1_3: Y_3_0 + Y_3_1 + Y_3_2 + Y_3_3 = 8
 constr2_0: Y_0_0 + Y_1_0 + Y_2_0 + Y_3_0 <= 7
 constr2_1: Y_0_1 + Y_1_1 + Y_2_1 + Y_3_1 <= 8
 constr2_2: Y_0_2 + Y_1_2 + Y_2_2 + Y_3_2 <= 10
 constr2_3: Y_0_3 + Y_1_3 + Y_2_3 + Y_3_3 <= 1000
 constr4_0: - 3 X_0_0 + Y_0_0 >= -0
 constr4_0: - 3 X_1_0 + Y_1_0 >= -0
 constr4_0: - 3 X_2_0 + Y_2_0 >= -0
 constr4_0: - 3 X_3_0 + Y_3_0 >= -0
 constr4_1: - 3 X_0_1 + Y_0_1 >= -0
 constr4_1: - 3 X_1_1 + Y_1_1 >= -0
 constr4_1: - 3 X_2_1 + Y_2_1 >= -0
 constr4_1: - 3 X_3_1 + Y_3_1 >= -0
 constr4_2: - 3 X_0_2 + Y_0_2 >= -0
 constr4_2: - 3 X_1_2 + Y_1_2 >= -0
 constr4_2: - 3 X_2_2 + Y_2_2 >= -0
 constr4_2: - 3 X_3_2 + Y_3_2 >= -0
 constr4_3: - 3 X_0_3 + Y_0_3 >= -0
 constr4_3: - 3 X_1_3 + Y_1_3 >= -0
 constr4_3: - 3 X_2_3 + Y_2_3 >= -0
 constr4_3: - 3 X_3_3 + Y_3_3 >= -0
 constr5_0: - 9 X_0_0 + Y_0_0 <= 0
 constr5_0: - 6 X_1_0 + Y_1_0 <= 0
 constr5_0: - 4 X_2_0 + Y_2_0 <= 0
 constr5_0: - 8 X_3_0 + Y_3_0 <= 0
 constr5_1: - 9 X_0_1 + Y_0_1 <= 0
 constr5_1: - 6 X_1_1 + Y_1_1 <= 0
 constr5_1: - 4 X_2_1 + Y_2_1 <= 0
 constr5_1: - 8 X_3_1 + Y_3_1 <= 0
 constr5_2: - 9 X_0_2 + Y_0_2 <= 0
 constr5_2: - 6 X_1_2 + Y_1_2 <= 0
 constr5_2: - 4 X_2_2 + Y_2_2 <= 0
 constr5_2: - 8 X_3_2 + Y_3_2 <= 0
 constr5_3: - 9 X_0_3 + Y_0_3 <= 0
 constr5_3: - 6 X_1_3 + Y_1_3 <= 0
 constr5_3: - 4 X_2_3 + Y_2_3 <= 0
 constr5_3: - 8 X_3_3 + Y_3_3 <= 0
 constr6_0: Y_0_0 >= -0
 constr6_0: Y_0_0 + Y_1_0 >= -0
 constr6_0: Y_0_0 + Y_1_0 + Y_2_0 >= -0
 constr6_0: Y_0_0 + Y_1_0 + Y_2_0 + Y_3_0 >= -0
 constr6_1: Y_0_1 >= 1
 constr6_1: Y_0_1 + Y_1_1 >= 1
 constr6_1: Y_0_1 + Y_1_1 + Y_2_1 >= 1
 constr6_1: Y_0_1 + Y_1_1 + Y_2_1 + Y_3_1 >= 1
 constr6_2: Y_0_2 >= 3
 constr6_2: Y_0_2 + Y_1_2 >= 3
 constr6_2: Y_0_2 + Y_1_2 + Y_2_2 >= 3
 constr6_2: Y_0_2 + Y_1_2 + Y_2_2 + Y_3_2 >= 3
 constrI_0: - X_0_0 - X_1_0 - X_2_0 - X_3_0 + 4 temp1_0 >= -0
 constrI_1: - X_0_1 - X_1_1 - X_2_1 - X_3_1 + 4 temp1_1 >= -0
 constrI_2: - X_0_2 - X_1_2 - X_2_2 - X_3_2 + 4 temp1_2 >= -0
 constrI_3: - X_0_3 - X_1_3 - X_2_3 - X_3_3 + 4 temp1_3 >= -0
 constrII_0: - Y_0_0 - Y_1_0 - Y_2_0 - Y_3_0 + Obj >= -0
 constrII_1: - Y_0_1 - Y_1_1 - Y_2_1 - Y_3_1 - 7 temp1_1 + Obj >= -0
 constrII_2: - Y_0_2 - Y_1_2 - Y_2_2 - Y_3_2 - 15 temp1_2 + Obj >= -0
 constrII_3: - Y_0_3 - Y_1_3 - Y_2_3 - Y_3_3 - 25 temp1_3 + Obj >= -0
Bounds
Binaries
 X_0_0 X_0_1 X_0_2 X_0_3 X_1_0 X_1_1 X_1_2 X_1_3 X_2_0 X_2_1 X_2_2 X_2_3
 X_3_0 X_3_1 X_3_2 X_3_3 temp1_0 temp1_1 temp1_2 temp1_3
Generals
 Y_0_0 Y_0_1 Y_0_2 Y_0_3 Y_1_0 Y_1_1 Y_1_2 Y_1_3 Y_2_0 Y_2_1 Y_2_2 Y_2_3
 Y_3_0 Y_3_1 Y_3_2 Y_3_3
End
