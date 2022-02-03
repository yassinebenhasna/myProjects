﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Ce code a été généré par un outil.
//     Version du runtime :4.0.30319.42000
//
//     Les modifications apportées à ce fichier peuvent provoquer un comportement incorrect et seront perdues si
//     le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// Ce code source a été automatiquement généré par Microsoft.VSDesigner, Version 4.0.30319.42000.
// 
#pragma warning disable 1591

namespace WebApplication2.SWSoap {
    using System;
    using System.Web.Services;
    using System.Diagnostics;
    using System.Web.Services.Protocols;
    using System.Xml.Serialization;
    using System.ComponentModel;
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Web.Services.WebServiceBindingAttribute(Name="WebService1Soap", Namespace="http://tempuri.org/")]
    public partial class WebService1 : System.Web.Services.Protocols.SoapHttpClientProtocol {
        
        private System.Threading.SendOrPostCallback getMoyenneSemestreOperationCompleted;
        
        private System.Threading.SendOrPostCallback GetMoyenneAnneeOperationCompleted;
        
        private System.Threading.SendOrPostCallback GetMoyenneMasterMentionOperationCompleted;
        
        private System.Threading.SendOrPostCallback validationSemestreOperationCompleted;
        
        private bool useDefaultCredentialsSetExplicitly;
        
        /// <remarks/>
        public WebService1() {
            this.Url = global::WebApplication2.Properties.Settings.Default.WebApplication2_SWSoap_WebService1;
            if ((this.IsLocalFileSystemWebService(this.Url) == true)) {
                this.UseDefaultCredentials = true;
                this.useDefaultCredentialsSetExplicitly = false;
            }
            else {
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        public new string Url {
            get {
                return base.Url;
            }
            set {
                if ((((this.IsLocalFileSystemWebService(base.Url) == true) 
                            && (this.useDefaultCredentialsSetExplicitly == false)) 
                            && (this.IsLocalFileSystemWebService(value) == false))) {
                    base.UseDefaultCredentials = false;
                }
                base.Url = value;
            }
        }
        
        public new bool UseDefaultCredentials {
            get {
                return base.UseDefaultCredentials;
            }
            set {
                base.UseDefaultCredentials = value;
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        /// <remarks/>
        public event getMoyenneSemestreCompletedEventHandler getMoyenneSemestreCompleted;
        
        /// <remarks/>
        public event GetMoyenneAnneeCompletedEventHandler GetMoyenneAnneeCompleted;
        
        /// <remarks/>
        public event GetMoyenneMasterMentionCompletedEventHandler GetMoyenneMasterMentionCompleted;
        
        /// <remarks/>
        public event validationSemestreCompletedEventHandler validationSemestreCompleted;
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://tempuri.org/getMoyenneSemestre", RequestNamespace="http://tempuri.org/", ResponseNamespace="http://tempuri.org/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public double getMoyenneSemestre(string apogee, string semestre) {
            object[] results = this.Invoke("getMoyenneSemestre", new object[] {
                        apogee,
                        semestre});
            return ((double)(results[0]));
        }
        
        /// <remarks/>
        public void getMoyenneSemestreAsync(string apogee, string semestre) {
            this.getMoyenneSemestreAsync(apogee, semestre, null);
        }
        
        /// <remarks/>
        public void getMoyenneSemestreAsync(string apogee, string semestre, object userState) {
            if ((this.getMoyenneSemestreOperationCompleted == null)) {
                this.getMoyenneSemestreOperationCompleted = new System.Threading.SendOrPostCallback(this.OngetMoyenneSemestreOperationCompleted);
            }
            this.InvokeAsync("getMoyenneSemestre", new object[] {
                        apogee,
                        semestre}, this.getMoyenneSemestreOperationCompleted, userState);
        }
        
        private void OngetMoyenneSemestreOperationCompleted(object arg) {
            if ((this.getMoyenneSemestreCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.getMoyenneSemestreCompleted(this, new getMoyenneSemestreCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://tempuri.org/GetMoyenneAnnee", RequestNamespace="http://tempuri.org/", ResponseNamespace="http://tempuri.org/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public double GetMoyenneAnnee(string apogee, string annee, string semestre1, string semestre2) {
            object[] results = this.Invoke("GetMoyenneAnnee", new object[] {
                        apogee,
                        annee,
                        semestre1,
                        semestre2});
            return ((double)(results[0]));
        }
        
        /// <remarks/>
        public void GetMoyenneAnneeAsync(string apogee, string annee, string semestre1, string semestre2) {
            this.GetMoyenneAnneeAsync(apogee, annee, semestre1, semestre2, null);
        }
        
        /// <remarks/>
        public void GetMoyenneAnneeAsync(string apogee, string annee, string semestre1, string semestre2, object userState) {
            if ((this.GetMoyenneAnneeOperationCompleted == null)) {
                this.GetMoyenneAnneeOperationCompleted = new System.Threading.SendOrPostCallback(this.OnGetMoyenneAnneeOperationCompleted);
            }
            this.InvokeAsync("GetMoyenneAnnee", new object[] {
                        apogee,
                        annee,
                        semestre1,
                        semestre2}, this.GetMoyenneAnneeOperationCompleted, userState);
        }
        
        private void OnGetMoyenneAnneeOperationCompleted(object arg) {
            if ((this.GetMoyenneAnneeCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.GetMoyenneAnneeCompleted(this, new GetMoyenneAnneeCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://tempuri.org/GetMoyenneMasterMention", RequestNamespace="http://tempuri.org/", ResponseNamespace="http://tempuri.org/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public string GetMoyenneMasterMention(string apogee) {
            object[] results = this.Invoke("GetMoyenneMasterMention", new object[] {
                        apogee});
            return ((string)(results[0]));
        }
        
        /// <remarks/>
        public void GetMoyenneMasterMentionAsync(string apogee) {
            this.GetMoyenneMasterMentionAsync(apogee, null);
        }
        
        /// <remarks/>
        public void GetMoyenneMasterMentionAsync(string apogee, object userState) {
            if ((this.GetMoyenneMasterMentionOperationCompleted == null)) {
                this.GetMoyenneMasterMentionOperationCompleted = new System.Threading.SendOrPostCallback(this.OnGetMoyenneMasterMentionOperationCompleted);
            }
            this.InvokeAsync("GetMoyenneMasterMention", new object[] {
                        apogee}, this.GetMoyenneMasterMentionOperationCompleted, userState);
        }
        
        private void OnGetMoyenneMasterMentionOperationCompleted(object arg) {
            if ((this.GetMoyenneMasterMentionCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.GetMoyenneMasterMentionCompleted(this, new GetMoyenneMasterMentionCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("http://tempuri.org/validationSemestre", RequestNamespace="http://tempuri.org/", ResponseNamespace="http://tempuri.org/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public bool validationSemestre(string apogee, string semestre) {
            object[] results = this.Invoke("validationSemestre", new object[] {
                        apogee,
                        semestre});
            return ((bool)(results[0]));
        }
        
        /// <remarks/>
        public void validationSemestreAsync(string apogee, string semestre) {
            this.validationSemestreAsync(apogee, semestre, null);
        }
        
        /// <remarks/>
        public void validationSemestreAsync(string apogee, string semestre, object userState) {
            if ((this.validationSemestreOperationCompleted == null)) {
                this.validationSemestreOperationCompleted = new System.Threading.SendOrPostCallback(this.OnvalidationSemestreOperationCompleted);
            }
            this.InvokeAsync("validationSemestre", new object[] {
                        apogee,
                        semestre}, this.validationSemestreOperationCompleted, userState);
        }
        
        private void OnvalidationSemestreOperationCompleted(object arg) {
            if ((this.validationSemestreCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.validationSemestreCompleted(this, new validationSemestreCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        public new void CancelAsync(object userState) {
            base.CancelAsync(userState);
        }
        
        private bool IsLocalFileSystemWebService(string url) {
            if (((url == null) 
                        || (url == string.Empty))) {
                return false;
            }
            System.Uri wsUri = new System.Uri(url);
            if (((wsUri.Port >= 1024) 
                        && (string.Compare(wsUri.Host, "localHost", System.StringComparison.OrdinalIgnoreCase) == 0))) {
                return true;
            }
            return false;
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    public delegate void getMoyenneSemestreCompletedEventHandler(object sender, getMoyenneSemestreCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class getMoyenneSemestreCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal getMoyenneSemestreCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public double Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((double)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    public delegate void GetMoyenneAnneeCompletedEventHandler(object sender, GetMoyenneAnneeCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class GetMoyenneAnneeCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal GetMoyenneAnneeCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public double Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((double)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    public delegate void GetMoyenneMasterMentionCompletedEventHandler(object sender, GetMoyenneMasterMentionCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class GetMoyenneMasterMentionCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal GetMoyenneMasterMentionCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public string Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((string)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    public delegate void validationSemestreCompletedEventHandler(object sender, validationSemestreCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.8.4084.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class validationSemestreCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal validationSemestreCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public bool Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((bool)(this.results[0]));
            }
        }
    }
}

#pragma warning restore 1591