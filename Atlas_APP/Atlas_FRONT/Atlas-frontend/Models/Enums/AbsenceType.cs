using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;

namespace Atlas_frontend.Models.Enums
{
    public class AbsenceType
    {
        public const string Maladie = "Maladie";
        public const string NonSolde = "Non soldé";
        public const string Conge = "Congé";
        public const string Maternel = "Maternel";
        public const string Paternel = "Paternel";
        public const string Recuperation = "Récupération";

        public static List<String> getValues()
        {
            List<String> values = new List<String>();
            Type type = typeof(AbsenceType);

            foreach (FieldInfo p in type.GetFields(BindingFlags.Static | BindingFlags.Public))
            {
                values.Add((string)p.GetValue(null));
            }

            return values;
        }
    }
}
