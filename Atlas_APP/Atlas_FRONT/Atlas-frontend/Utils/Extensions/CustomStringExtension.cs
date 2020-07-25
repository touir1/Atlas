using Microsoft.CodeAnalysis.CSharp.Syntax;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Utils.Extensions
{
    public static class CustomStringExtension
    {
        public static string Capitalize(this String value)
        {
            if (value == null) return null;
            if (value.Length == 0) return value;
            if (!value.Any(char.IsLetter)) return value;
            return string.Join(" ",value
                .Split(" ")
                .ToList()
                .Select(s => s.Length > 0 ? char.ToUpper(s[0]) + (s.Length > 1 ? s.Substring(1) : "") : s));

        }
    }
}
