package com.nada.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nada.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsDataActivity extends AppCompatActivity {

//    @BindView(R.id.tv_about)
//    TextView tv_about;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.webView)
    WebView webView;

    String about_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_about_us_data);
        ButterKnife.bind(this);

        about_type = getIntent().getStringExtra("about_type");

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (about_type.equalsIgnoreCase("vision")) {
//            tv_about.setText(Html.fromHtml(vision));
            webView.loadUrl("file:///android_asset/html/vision.html");
        } else if (about_type.equalsIgnoreCase("primary_function")) {
//            tv_about.setText(Html.fromHtml(primary_function));
            webView.loadUrl("file:///android_asset/html/primary_function.html");
        } else if (about_type.equalsIgnoreCase("governing_body")) {
//            tv_about.setText(Html.fromHtml(governing_function));
            webView.loadUrl("file:///android_asset/html/governing.html");
        } else if (about_type.equalsIgnoreCase("anti_doping_appeal_panel")) {
//            tv_about.setText(Html.fromHtml(anti_doping_apeal_panel));
            webView.loadUrl("file:///android_asset/html/appealpanel.html");
        } else if (about_type.equalsIgnoreCase("anti_doping_disciplinary_panel")) {
//            tv_about.setText(Html.fromHtml(anti_doping_disciplinary_panel));
            webView.loadUrl("file:///android_asset/html/disciplinarypanel.html");
        } else if (about_type.equalsIgnoreCase("therapeutic")) {
//            tv_about.setText(Html.fromHtml(therapeutic_content));
            webView.loadUrl("file:///android_asset/html/therapeautic.html");
        } else if (about_type.equalsIgnoreCase("who_is_who")) {
//            tv_about.setText(Html.fromHtml(title_who_is_who));
            webView.loadUrl("file:///android_asset/html/whoiswho.html");
        }


//        webView.loadUrl("file:///android_asset/html/therapeautic.html");

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.ic_header);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }


//    String title_vision = "Vision";
//    String vision = "<h2 class=\"page-name\">Vision</h2>\n" +
//            "\t\t\t\t\t\t<h2><strong>The National Anti-Doping Programme</strong></h2>\n" +
//            "\n" +
//            "<p>The National Anti Doping Agency [NADA] was established by the Government of India, with the objective of acting as the independent Anti-Doping Organization for India having a vision of dope free sports. The NADA has the necessary authority and responsibility for:</p>\n" +
//            "\n" +
//            "<ul>\n" +
//            "\t<li>Planning, coordinating, implementing, monitoring and advocating improvements in Doping Control;</li>\n" +
//            "\t<li>Cooperating with other relevant national organizations, agencies and other Anti-Doping Organizations;</li>\n" +
//            "\t<li>Encouraging reciprocal Testing between National Anti-Doping Organizations;</li>\n" +
//            "\t<li>Promoting anti-doping research;</li>\n" +
//            "\t<li>Where funding is provided, withholding some or all funding, during any period of his or her ineligibility, to any Athlete or Athlete Support Personnel who has violated anti-doping rules;</li>\n" +
//            "\t<li>Vigorously pursuing all potential anti-doping rule violations within its jurisdiction including investigating into whether Athlete Support Personnel or other Persons may have been involved in each case of doping.</li>\n" +
//            "\t<li>Planning, implementing and monitoring anti doping information and education programs.</li>\n" +
//            "</ul>\n" +
//            "\n" +
//            "<p>&nbsp;</p>";
//
//    String title_primary = "Primary Functions";
//    String primary_function = "<h2 class=\"page-name\">Primary Functions</h2>\n" +
//            "\t\t\t\t\t\t<p>National Anti Doping Agency is mandated for Dope free sports in India. The primary objectives are to implement anti-doping rules as per WADA code, regulate dope control programme, to promote education and research and creating awareness about doping and its ill effects.</p>\n" +
//            "\n" +
//            "<p>&nbsp;</p>\n" +
//            "\n" +
//            "<p>The primary functions of NADA are as under:</p>\n" +
//            "\n" +
//            "<ul>\n" +
//            "\t<li>To&nbsp;implement&nbsp;the&nbsp;Anti&nbsp;Doping&nbsp;Code&nbsp;to&nbsp;achieve&nbsp;compliance&nbsp;by&nbsp;all&nbsp;sports&nbsp;organizations&nbsp; in&nbsp;the&nbsp;Country.</li>\n" +
//            "\t<li>To&nbsp;coordinate&nbsp;dope&nbsp;testing&nbsp;program&nbsp;through&nbsp;all&nbsp;participating&nbsp;stakeholders.&nbsp;</li>\n" +
//            "\t<li>To&nbsp;promote&nbsp;anti&nbsp;doping&nbsp;research&nbsp;and&nbsp;education&nbsp;to&nbsp;inculcate&nbsp;the&nbsp;value&nbsp;of&nbsp;dope&nbsp;free&nbsp;sports.</li>\n" +
//            "\t<li>To&nbsp;adopt&nbsp;best&nbsp;practice&nbsp;standards&nbsp;and&nbsp;quality&nbsp;systems&nbsp;to&nbsp;enable&nbsp;effective&nbsp;implementation&nbsp;and&nbsp; continual&nbsp;improvement&nbsp;of&nbsp;the&nbsp;program.</li>\n" +
//            "</ul>";
//
//    String title_governing = "Governing Body";
//    String governing_function = "<h2 class=\"page-name\">Governing Body</h2>\n" +
//            "\t\t\t\t\t\t<h2><strong>General Body Members of NADA</strong></h2>\n" +
//            "\n" +
//            "<table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t<tbody>\n" +
//            "\t\t<tr class=\"td-first-box-noheader\">\n" +
//            "\t\t\t<td><strong>S. No</strong></td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p><strong>Name</strong></p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p><strong>Category/ Designation</strong></p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td><strong>Position </strong></td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>1.</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Shri Kiren&nbsp; Rijiju</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Hon,ble Minister of State (I/c), Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Chairman</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>2</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Shri&nbsp;Radhey Shyam Julaniya</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Secretary, Department of Sports, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Vice-Chairman</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>3</td>\n" +
//            "\t\t\t<td width=\"300\">Dr. S. Venkatesh</td>\n" +
//            "\t\t\t<td>Director General, Health Services, Ministry of Health &amp; Family Welfare</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>4</td>\n" +
//            "\t\t\t<td width=\"300\">Smt. Kiran Soni Gupta</td>\n" +
//            "\t\t\t<td>Additional Secretary &amp; Financial Advisor, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>5</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Smt. Neelam Kapur</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Director General, Sports Authority of India</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>6</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Sh. Inder Dhamija</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Joint Secretary (Sports), Department of Sports, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>7</td>\n" +
//            "\t\t\t<td width=\"300\">Sh. Narinder Dhruv Batra</td>\n" +
//            "\t\t\t<td>President, Indian Olympic Association</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\t\t\tEx-officio</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>8</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Justice (Retd) Ajit Bharihok</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>New Delhi</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>9</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Dr. C. D. Tripathi</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Director, Professor &amp; Head of Pharmacology</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>10</td>\n" +
//            "\t\t\t<td width=\"300\">Sh. Ajay Singh</td>\n" +
//            "\t\t\t<td>President, Boxing Federation of India</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>11</td>\n" +
//            "\t\t\t<td width=\"300\">Ms. Anjum Chopra</td>\n" +
//            "\t\t\t<td>Former Cricketer</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>12</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Sh. Maha Singh Rao</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Wrestling Coach</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>13</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Ms. Elena Norman</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>CEO, Hockey India</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>14</td>\n" +
//            "\t\t\t<td width=\"300\">Sh. Suresh Sharma</td>\n" +
//            "\t\t\t<td>Former Kho-Kho player</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>15</td>\n" +
//            "\t\t\t<td width=\"300\">Sh. Navin Agarwal</td>\n" +
//            "\t\t\t<td>Director General, NADA</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member Secretary</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t</tbody>\n" +
//            "</table>\n" +
//            "\n" +
//            "<p><strong>Governing Body Members of NADA</strong></p>\n" +
//            "\n" +
//            "<p>&nbsp;</p>\n" +
//            "\n" +
//            "<table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t<tbody>\n" +
//            "\t\t<tr class=\"td-first-box-noheader\">\n" +
//            "\t\t\t<td><strong>S. No</strong></td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p><strong>Name</strong></p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p><strong>Category/ Designation</strong></p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td><strong>Position </strong></td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>1.</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Shri Kiren&nbsp; Rijiju</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Hon,ble Minister of State (I/c), Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Chairman</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>2</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Shri&nbsp;Radhey Shyam Julaniya</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Secretary, Department of Sports, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Vice-</p>\n" +
//            "\n" +
//            "\t\t\t<p>Chairman</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>3</td>\n" +
//            "\t\t\t<td width=\"300\">Dr. S. Venkatesh</td>\n" +
//            "\t\t\t<td>Director General, Health Services, Ministry of Health &amp; Family Welfare</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>4</td>\n" +
//            "\t\t\t<td width=\"300\">Smt. Kiran Soni Gupta</td>\n" +
//            "\t\t\t<td>Additional Secretary &amp; Financial Advisor, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>5</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Smt. Neelam Kapur</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Director General, Sports Authority of India</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>6</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Sh. Narinder Dhruv Batra</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>President, Indian Olympic Association</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>7</td>\n" +
//            "\t\t\t<td width=\"300\">Dr. Ashok Ahuja</td>\n" +
//            "\t\t\t<td>Anti-Doping Expert</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>8</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Sh. Navin Agarwal</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Director General, NADA</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member Secretary</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t</tbody>\n" +
//            "</table>\n" +
//            "\n" +
//            "<p>&nbsp;</p>\n" +
//            "\n" +
//            "<p><strong>Executive Board Members of NADA</strong></p>\n" +
//            "\n" +
//            "<p>&nbsp;</p>\n" +
//            "\n" +
//            "<table border=\"0\" cellpadding=\"5\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t<tbody>\n" +
//            "\t\t<tr class=\"td-first-box-noheader\">\n" +
//            "\t\t\t<td><strong>S. No</strong></td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p><strong>Name</strong></p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p><strong>Category/ Designation</strong></p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td><strong>Position </strong></td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>1.</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Shri&nbsp;Radhey Shyam Julaniya</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Secretary, Department of Sports, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Chairman</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>2</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Sh. Inder Dhamija</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Joint Secretary (Sports), Department of Sports, Ministry of Youth Affairs and Sports</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Vice-Chairman</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>3</td>\n" +
//            "\t\t\t<td width=\"300\">Dr. S. Venkatesh</td>\n" +
//            "\t\t\t<td>Director General, Health Services, Ministry of Health &amp; Family Welfare</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>4</td>\n" +
//            "\t\t\t<td width=\"300\">Justice (Retd) Ajit Bharihok</td>\n" +
//            "\t\t\t<td>New Delhi</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>5</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Dr. C. D. Tripathi</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Director, Professor &amp; Head of Pharmacology</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member</p>\n" +
//            "\n" +
//            "\t\t\t<p>Nominated</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>6</td>\n" +
//            "\t\t\t<td width=\"300\">\n" +
//            "\t\t\t<p>Sh. Navin Agarwal</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Director General, NADA</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Member Secretary</p>\n" +
//            "\n" +
//            "\t\t\t<p>Ex-officio</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t</tr>\n" +
//            "\t</tbody>\n" +
//            "</table>";
//
//
//    String title_anti_doping_apeal_panel = "";
//    String anti_doping_apeal_panel = "<h2 class=\"page-name\">Anti Doping Appeal Panel</h2>\n" +
//            "\t\t\t\t\t\t<p><strong>List of Chairman and Members of Anti Doping Appeal Panel:</strong></p>\n" +
//            "\n" +
//            "<p>&nbsp;</p>\n" +
//            "\n" +
//            "<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t<tbody>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td><strong>Sr. No.</strong></td>\n" +
//            "\t\t\t<td><strong>Name</strong></td>\n" +
//            "\t\t\t<td><strong>Designation</strong></td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>1</td>\n" +
//            "\t\t\t<td>Justice (Retd.) R. V. Easwar</td>\n" +
//            "\t\t\t<td>Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>2</td>\n" +
//            "\t\t\t<td>Ms. Vibha Dutta Makhija, Sr. Advocate</td>\n" +
//            "\t\t\t<td>Vice-Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>3</td>\n" +
//            "\t\t\t<td>Dr. Naveen Dang</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>4</td>\n" +
//            "\t\t\t<td>Dr Harsh Mahajan</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>5</td>\n" +
//            "\t\t\t<td>Mr. Virender Sehwag</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>6</td>\n" +
//            "\t\t\t<td>Mr. Vinay Lamba</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t</tbody>\n" +
//            "</table>";
//
//    String title_anti_doping_disciplinary_panel = "Anti Doping Disciplinary Panel";
//    String anti_doping_disciplinary_panel = "<h2 class=\"page-name\">Anti Doping Disciplinary Panel</h2>\n" +
//            "\t\t\t\t\t\t<p><strong>List of Chairman and Members of Anti Doping Disciplinary Panel:</strong></p>\n" +
//            "\n" +
//            "<p>&nbsp;</p>\n" +
//            "\n" +
//            "<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t<tbody>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td><strong>Sr.No.</strong></td>\n" +
//            "\t\t\t<td><strong>Name&nbsp;</strong></td>\n" +
//            "\t\t\t<td><strong>Designation</strong></td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>1</td>\n" +
//            "\t\t\t<td>Sh. Kuldeep Singh, (Retd.) District Judge</td>\n" +
//            "\t\t\t<td>Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>2</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Mr. Manik Dogra, Advocate</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Vice-Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>3</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Mr. Nalin Kohli, Advocate</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Vice-Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>4</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Ms. Beena Gupta, Advocate</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Vice-Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>5</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Ms. Surbhi Mehta, Advocate</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Vice-Chairman</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>6</td>\n" +
//            "\t\t\t<td>Col. Sanjeev Kumar</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>7</td>\n" +
//            "\t\t\t<td>Dr. Vinod Dogra</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>8</td>\n" +
//            "\t\t\t<td>Dr. Akriti Sharma</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>9</td>\n" +
//            "\t\t\t<td>Dr. Sumita Gupta</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>10</td>\n" +
//            "\t\t\t<td>Dr. Rana Chengappa</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>11</td>\n" +
//            "\t\t\t<td>Mr. Rohit Rajpal</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>12</td>\n" +
//            "\t\t\t<td>Ms. Reeth Abraham</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>13</td>\n" +
//            "\t\t\t<td>Mr. Akhil Kumar</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>14</td>\n" +
//            "\t\t\t<td>\n" +
//            "\t\t\t<p>Ms. Kunjarani Devi</p>\n" +
//            "\t\t\t</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>15</td>\n" +
//            "\t\t\t<td>Mr. Jagbir Singh</td>\n" +
//            "\t\t\t<td>Member</td>\n" +
//            "\t\t</tr>\n" +
//            "\t</tbody>\n" +
//            "</table>";
//
//    String title_who_is_who = "Who is who";
//    String who_content = "<h2 class=\"page-name\">Who is who</h2>\n" +
//            "\t\t\t\t\t\t<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t<tbody>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td><strong>S. No.</strong></td>\n" +
//            "\t\t\t<td><strong>Name</strong></td>\n" +
//            "\t\t\t<td><strong>Designation</strong></td>\n" +
//            "\t\t\t<td><strong>Telephone No.</strong></td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>1.</td>\n" +
//            "\t\t\t<td>Sh. Navin Agarwal</td>\n" +
//            "\t\t\t<td>Director General</td>\n" +
//            "\t\t\t<td>011-24368243</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>2.</td>\n" +
//            "\t\t\t<td>Dr. Saravana Perumal S.</td>\n" +
//            "\t\t\t<td>Senior Project Officer</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>3.</td>\n" +
//            "\t\t\t<td>Dr. Ankush Gupta</td>\n" +
//            "\t\t\t<td>Project Officer</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>4.</td>\n" +
//            "\t\t\t<td>Mr.&nbsp;Ajeet Singh</td>\n" +
//            "\t\t\t<td>Administrative cum Account Officer</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>5.</td>\n" +
//            "\t\t\t<td>Mr. Bhulinder Jeet Verma</td>\n" +
//            "\t\t\t<td>Asst. Project Officer</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>6.</td>\n" +
//            "\t\t\t<td>Mr. Jay Singh</td>\n" +
//            "\t\t\t<td>Asst. Project Officer</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>7.</td>\n" +
//            "\t\t\t<td>Mr. Surander Singh Pundir</td>\n" +
//            "\t\t\t<td>Panel Assistant</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t\t<tr>\n" +
//            "\t\t\t<td>8.</td>\n" +
//            "\t\t\t<td>Ms. Hema</td>\n" +
//            "\t\t\t<td>Account Assistant</td>\n" +
//            "\t\t\t<td>011-24368274</td>\n" +
//            "\t\t</tr>\n" +
//            "\t</tbody>\n" +
//            "</table>";
//
//
//    String title_therapeutic = "Therapeutic Use Exemption Committee";
//    String therapeutic_content = "\n" +
//            "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">\n" +
//            "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
//            "\t<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,maximum-scale=1.0\" />\n" +
//            "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.nadaindia.org/assets/public/css/style.css\">\n" +
//            "</head>\n" +
//            "<body>\n" +
//            "\t<div class=\"common-page bulitinpage\">\n" +
//            "\t\t<div class=\"content\">\n" +
//            "\t\t\t<h2 class=\"page-name\">Therapeutic Use Exemption Committee</h2>\n" +
//            "\t\t\t<h3><span id=\"ctl00_content2_lblContentEng\"><strong>List of Chairman and Members of Therapeutic Use Exemption Committee:</strong></span></h3>\n" +
//            "\t\t\t<p>&nbsp;</p>\n" +
//            "\t\t\t<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"inner_draw\" style=\"width:100%\">\n" +
//            "\t\t\t\t<tbody>\n" +
//            "\t\t\t\t\t<tr class=\"td-first-box-noheader\">\n" +
//            "\t\t\t\t\t\t<td>\n" +
//            "\t\t\t\t\t\t\t<p>1</p>\n" +
//            "\t\t\t\t\t\t</td>\n" +
//            "\t\t\t\t\t\t<td>\n" +
//            "\t\t\t\t\t\t\t<p>Dr. H. L. Nag, Professor, Orthopedics- Chairman</p>\n" +
//            "\t\t\t\t\t\t</td>\n" +
//            "\t\t\t\t\t</tr>\n" +
//            "\t\t\t\t\t<tr>\n" +
//            "\t\t\t\t\t\t<td>2</td>\n" +
//            "\t\t\t\t\t\t<td>Dr. D. Bhattacharya, Sr. CMO, Pulmonary, Critical Care &amp; Sleep Medicine- Member</td>\n" +
//            "\t\t\t\t\t</tr>\n" +
//            "\t\t\t\t\t<tr>\n" +
//            "\t\t\t\t\t\t<td>3</td>\n" +
//            "\t\t\t\t\t\t<td>Dr. Shalini Chawla, Professor, Pharmacology- Member</td>\n" +
//            "\t\t\t\t\t</tr>\n" +
//            "\t\t\t\t</tbody>\n" +
//            "\t\t\t</table>\n" +
//            "\t\t</div>\n" +
//            "\t</div>\n" +
//            "</body>\n" +
//            "</html>";

}
