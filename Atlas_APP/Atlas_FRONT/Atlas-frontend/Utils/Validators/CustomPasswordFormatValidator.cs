using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Globalization;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Validators
{
    public class CustomPasswordFormatValidator : ValidationAttribute
    {
        public const string ValidationErrorMessage = "Password needs to have Upper, Lower, Numeric, Symbol characters and have a length of 8 to 30 characters";
        public override bool IsValid(object value)
        {
            if(value is string)
            {
                string password = (string) value;
                if(
                    password == null 
                    || password.Length < 8 || password.Length > 30 
                    || !password.Any(char.IsUpper) || !password.Any(char.IsLower)
                    || !password.Any(char.IsDigit) || !password.Any(char.IsSymbol)
                    )
                {
                    return false;
                }
            }
            return true;
        }
    }
}
