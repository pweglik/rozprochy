
#ifndef CALC_ICE
#define CALC_ICE

module Demo
{
  enum operation { MIN, MAX, AVG };

  exception NoInput {};

  sequence<float> Numbers;

  class Result {
    float result;
    optional(3) float precision;
  };

  interface Calc
  {
    Result add(float a, float b);
    void setPrecision(int precision);
    float avg(Numbers numbers, optional(1) bool geometric);
  };

};

#endif
