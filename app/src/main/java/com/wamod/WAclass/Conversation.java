package com.wamod.WAclass;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.*;
import com.wamod.*;
import com.wamod.activity.conversation.ConversationActivity;
import com.wamod.entry.*;
import com.whatsapp.DialogToastListActivity;

/**
 * Created by brianvalente on 7/9/15.
 */
public class Conversation extends DialogToastListActivity {
    public static void _onBackPressed(DialogToastListActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Utils.prefs.getBoolean("overview_multiplechats", true)) {
            activity.moveTaskToBack(true);
        } else {
            activity.finish();
        }
    }

    public static int getDateTVColor(TextView dateTV) {
        if (dateTV.getPaddingBottom() < 1) return ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_DATE);
        else return ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_DATE);
    }

    public static void call_getDateTVColor() {
        TextView tv = null;
        tv.setTextColor(getDateTVColor(tv));
    }


    public static void changeConversationTitleTextColor(TextView tv) {
        tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE));
    }

    public static void changeConversationSubtitleTextColor(TextView tv) {
        tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE));
    }

    /* Called on
     *    com.whatsapp.Conversation
     * Where
     *    Where setShowAsAction is called, replacing it
     * Smali
     *    invoke-static {}, Lcom/wamod/WAclass/Conversation;->getConversationAttachButtonBoolean()Z
     *    move-result v2
     *    if-nez v2, :cond_9
     *
     *    invoke-static {v0, v1}, Landroid/support/v4/view/MenuItemCompat;->setShowAsAction(Landroid/view/MenuItem;I)V
     *
     *    :cond_9
     */
    public static boolean getConversationAttachButtonBoolean() {
        return Utils.prefs.getBoolean("conversation_hidetoolbarattach", false);
    }

    public static void changeConversationBackButtonColor(LinearLayout back) {
        ImageView up = (ImageView) back.getChildAt(0);
        Drawable arrow = up.getDrawable();
        arrow.setColorFilter(Color.parseColor("#" + Utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        up.setImageDrawable(arrow);
    }

    /*public static String getBubbleColor(int optionID) {
        String value = "";
        switch (optionID) {
            case 0:
                value = Utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF");
                break;
            case 1:
                value = Utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF");
                break;
            case 2:
                value = Utils.prefs.getString("conversation_rightbubbledatecolor", "FFFFFF");
                break;
            case 3:
                value = Utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF");
                break;
            case 4:
                value = Utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF");
                break;
            case 5:
                value = Utils.prefs.getString("conversation_leftbubbledatecolor", "FFFFFF");
                break;
            case 6:
                value = Utils.prefs.getString("conversation_customparticipantcolor", "FFFFFF");
                break;
            default:
                break;
        }
        return value;
    }*/

    public static int getBubbleDrawableHex(int optionID) {
        String bubbleID = Utils.prefs.getString("conversation_style_bubble", "0");
        int incoming_normal = 0, incoming_normal_ext = 0, outgoing_normal = 0, outgoing_normal_ext = 0;
        switch (bubbleID) {
            case "0":
            default:
                incoming_normal = Resources.getHexID("balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("balloon_outgoing_normal_ext", "drawable");
                break;
            case "1":
                incoming_normal = Resources.getHexID("wamod_style_bubble_wamod_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_wamod_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_wamod_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_wamod_balloon_outgoing_normal_ext", "drawable");
                break;
            case "2":
                incoming_normal = Resources.getHexID("wamod_style_bubble_materialized_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_materialized_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_materialized_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_materialized_balloon_outgoing_normal_ext", "drawable");
                break;
            case "3":
                incoming_normal = Resources.getHexID("wamod_style_bubble_lb_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_lb_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_lb_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_lb_balloon_outgoing_normal_ext", "drawable");
                break;
            case "4":
                incoming_normal = Resources.getHexID("wamod_style_bubble_hangouts_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_hangouts_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_hangouts_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_hangouts_balloon_outgoing_normal_ext", "drawable");
                break;
            case "5":
                incoming_normal = Resources.getHexID("wamod_style_bubble_rounded_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_rounded_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_rounded_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_rounded_balloon_outgoing_normal_ext", "drawable");
                break;
            case "6":
                incoming_normal = Resources.getHexID("wamod_style_bubble_fbm_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_fbm_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_fbm_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_fbm_balloon_outgoing_normal_ext", "drawable");
                break;
            case "7":
                incoming_normal = Resources.getHexID("wamod_style_bubble_newhangouts_balloon_incoming_normal", "drawable");
                incoming_normal_ext = Resources.getHexID("wamod_style_bubble_newhangouts_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = Resources.getHexID("wamod_style_bubble_newhangouts_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = Resources.getHexID("wamod_style_bubble_newhangouts_balloon_outgoing_normal_ext", "drawable");
                break;
        }

        switch (optionID) {
            case 0:
                return incoming_normal;
            case 1:
                return incoming_normal_ext;
            case 2:
                return outgoing_normal;
            case 3:
                return outgoing_normal_ext;
        }

        return incoming_normal;
    }

    public static int getConversationEntryID() {
        return Integer.parseInt(Utils.prefs.getString("conversation_style_entry", "0"));
    }

    public static Intent conversationMultitask(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Utils.prefs.getBoolean("overview_multiplechats", true)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }


    /* Called on
     *    com.whatsapp.Conversation
     * Where
     *    Where 0x7f030049 is located
     * Smali
     *    invoke-static {}, Lcom/wamod/WAclass/Conversation;->getActionBarStyle()I
     *    move-result v1
     */
    public static int getActionBarStyle() {
        String config = Utils.prefs.getString("conversation_style_toolbar", "0");
        int id = 0;
        switch (config) {
            case "0":
                id = Resources.getLayout("wamod_style_stock_conversation_actionbar");
                break;
            case "1":
                id = Resources.getLayout("wamod_style_stockcentered_conversation_actionbar");
                break;
            case "2":
                id = Resources.getLayout("wamod_style_wamod_conversation_actionbar");
                break;
            case "3":
                id = Resources.getLayout("wamod_style_wamodcentered_conversation_actionbar");
                break;
        }
        return id;
    }

    public static void setTaskDescription(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String contactName = ((TextView)activity.findViewById(Resources.id.conversation_contact_name)).getText().toString();
            String title = activity.getString(Resources.string.app_name);
            if (Utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getString(Resources.string.chat_with, contactName);
            int color = Color.parseColor("#075e54");
            if (Utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + Utils.prefs.getString("general_toolbarcolor", "ffffff"));
            try {
                ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), Resources.drawable.icon), color);
                activity.setTaskDescription(taskDesc);
            } catch (Exception e) {
                Toast.makeText(Utils.context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            for (int i = 0; i < Utils.openedChats.size(); i++) {
                Chat chat = Utils.openedChats.get(i);
                if (chat.name.contentEquals(contactName)) {
                    chat.activity.finishAndRemoveTask();
                    Utils.openedChats.remove(i);
                }
            }
            Utils.openedChats.add(new Chat(contactName,activity));
        }
    }


    public static void initConversation(com.whatsapp.Conversation a) {

        if (Utils.prefs.getBoolean("debugging_wamodconversationactivity", false)) {
            Intent intent = new Intent(a, ConversationActivity.class);
            intent.putExtra("jid", a.getIntent().getStringExtra("jid"));
            a.startActivity(intent);
            a.finish();
        }

        ViewGroup cntnt = (ViewGroup) a.findViewById(android.R.id.content);
        if (cntnt.getTag(Resources.id.bullet) == null) cntnt.setTag(Resources.id.bullet, true);
        else return;


        // Init attachments
        a.F();

        // Load contact
        com.whatsapp.Conversation.g(a);

        // Load colors
        setTaskDescription(a);


        // Hides profile photo if activated
        if (Utils.prefs.getBoolean("conversation_hideprofilephoto", false)) {
            ImageView profilePhoto = (ImageView) a.findViewById(Resources.id.conversation_contact_photo);
            if (profilePhoto != null) {
                profilePhoto.getLayoutParams().width = 0;
                profilePhoto.getLayoutParams().height = 0;
                profilePhoto.setVisibility(View.GONE);
            }
        }


        // Change background color if activated
        if (Utils.prefs.getBoolean("conversation_custombackcolorbool", false)) {
            ImageView bg = (ImageView) a.findViewById(Resources.id.conversation_background);
            bg.setVisibility(View.GONE);
            View content = a.findViewById(android.R.id.content);
            content.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BACKGROUND));
        }


        // Initializes the entry style
        switch (Utils.prefs.getString("conversation_style_entry","0")) {
            case "0":
                // Stock
                Stock.init(a);
                break;
            case "1":
                // WAMOD
                WAMOD.init(a);
                break;
            case "2":
                // Hangouts
                Hangouts.init(a);
                break;
            case "3":
                // Simple
                Simple.init(a);
                break;
            case "4":
                // Aran
                Aran.init(a);
                break;
            case "5":
                // Mood
                Mood.init(a);
                break;
            case "6":
                // Test
                Test.init(a);
                break;
        }


        if (Utils.prefs.getBoolean("conversation_toolbarexit", false)) {
            ImageView back = (ImageView) a.findViewById(Resources.id.up);
            //back.setImageBitmap(new BitmapFactory(activity.getResources().getDrawable(replace_ids_here.ic_action_close)));
            Drawable x = a.getResources().getDrawable(Resources.drawable.wamod_action_close);
            x.setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS), PorterDuff.Mode.MULTIPLY);
            back.setImageDrawable(x);
        }
    }

    public void callInitConversation() {
        initConversation(null);
    }


    /* Called on
     *    com.whatsapp.Conversation, where Lcom/whatsapp/ConversationRowsActivity;->onCreateOptionsMenu(Landroid/view/Menu;)Z is located
     * Where
     *    Before return v0
     * Smali
     *    invoke-static {p0}, Lcom/wamod/WAclass/Conversation;->tintToolbarButtons(Lcom/whatsapp/Conversation;)V
     */
    public static void tintToolbarButtons(com.whatsapp.Conversation a) {
        ViewGroup toolbar = (ViewGroup) a.findViewById(Resources.id.toolbar);
        View linearLayoutCompat = toolbar.getChildAt(2);
        if (linearLayoutCompat != null) {
            final LinearLayoutCompat LinearLayoutCompat2 = (LinearLayoutCompat) linearLayoutCompat;
            linearLayoutCompat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    for (int i = 0; i < LinearLayoutCompat2.getChildCount(); i++) {
                        final View child = LinearLayoutCompat2.getChildAt(i);
                        if (child != null) {
                            child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    if (child instanceof TextView) {
                                        TextView tv = (TextView) child;
                                        Drawable[] icon = tv.getCompoundDrawables();
                                        icon[0].setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS), PorterDuff.Mode.MULTIPLY);
                                        tv.setCompoundDrawables(icon[0], icon[1], icon[2], icon[3]);
                                    } else if (child instanceof ImageButton) {
                                        ImageButton im = (ImageButton) child;
                                        im.setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS));
                                    } else if (child instanceof ImageView) {
                                        ImageView im = (ImageView) child;
                                        im.setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS));
                                    }
                                    child.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                }
                            });
                        }
                    }
                    LinearLayoutCompat2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }


    /* Called on
     *    com.whatsapp.Conversation.h()Z
     * Where
     *    Before return v0
     * Smali
     *    invoke-static {p0}, Lcom/wamod/WAclass/Conversation;->_startSupportActionMode(Lcom/whatsapp/Conversation;)V
     */
    public static void _startSupportActionMode(final com.whatsapp.Conversation a) {
        ViewGroup action_mode_bar = (ViewGroup) a.findViewById(Resources.id.action_mode_bar);
        if (action_mode_bar != null) {
            action_mode_bar.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR));
            final ImageView action_mode_close_button = (ImageView) a.findViewById(Resources.id.action_mode_close_button);
            final TextView action_bar_title = (TextView) a.findViewById(Resources.id.action_bar_title);
            final TextView menuitem_delete  = (TextView) a.findViewById(Resources.id.menuitem_delete);

            if (menuitem_delete != null) menuitem_delete.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ViewGroup parent = (ViewGroup) menuitem_delete.getParent();
                    if (parent != null) {
                        for (int i = 0; i < parent.getChildCount(); i++) {
                            View v = parent.getChildAt(i);
                            if (v instanceof TextView) {
                                TextView tv = (TextView) v;
                                Drawable[] icon = tv.getCompoundDrawables();
                                icon[0] = Utils.tintToColor(icon[0], ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS));
                                tv.setCompoundDrawables(icon[0], null, null, null);
                            } else if (v instanceof ImageView) {
                                ImageView im = (ImageView) v;
                                im.setImageDrawable(Utils.tintToColor(im.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS)));
                            }
                        }
                    }
                    action_mode_close_button.setImageDrawable(Utils.tintToColor(action_mode_close_button.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS)));
                    if (action_bar_title != null) action_bar_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE));
                }
            });

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) a.getWindow().setStatusBarColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_STATUSBAR));
    }

    private void callSAM() {
        _startSupportActionMode(null);
    }


    /* Called on
     *    com.whatsapp.Conversation
     * Where
     *    Where 0x7f030048 is located
     * Smali
     *    const/4 v0, 0x0
     *    invoke-static {v0}, Lcom/wamod/WAclass/Conversation;->getConversationEntry(I)I
     *    move-result v0
     */
    public static int getConversationEntry(int id) {
        int activeTheme = Integer.parseInt(Utils.prefs.getString("conversation_style_entry", "0"));
        int conversation = 0, emoji_picker_horizontal = 0;
        switch (activeTheme) {
            default:
            case 0:
                conversation = Resources.getLayout("conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
            case 1:
                conversation = Resources.getLayout("wamod_theme_wamod_conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
            case 2:
                conversation = Resources.getLayout("wamod_theme_hangouts_conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
            case 3:
                conversation = Resources.getLayout("wamod_theme_simple_conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
            case 4:
                conversation = Resources.getLayout("wamod_theme_aran_conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
            case 5:
                conversation = Resources.getLayout("wamod_theme_mood_conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
            case 6:
                conversation = Resources.getLayout("wamod_theme_test_conversation");
                emoji_picker_horizontal = Resources.getLayout("emoji_picker_horizontal");
                break;
        }
        switch (id) {
            case 0:
                return conversation;
            case 1:
                return emoji_picker_horizontal;
        }
        return conversation;
    }
}
