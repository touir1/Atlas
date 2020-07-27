using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;

namespace Atlas_frontend.Models.Enums
{
    public class AbsenceStatus
    {
        public const string AValider = "A valider";
        public const string Valide = "Validé";
        public const string Refuse = "Refusé";

        public static List<String> getValues()
        {
            List<String> values = new List<String>();
            Type type = typeof(AbsenceStatus);

            foreach (FieldInfo p in type.GetFields(BindingFlags.Static | BindingFlags.Public))
            {
                values.Add((string)p.GetValue(null));
            }

            return values;
        }
    }
}
